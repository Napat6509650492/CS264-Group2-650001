package th.ac.tu.cs.services.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import th.ac.tu.cs.services.model.Login.UserPassword;

import java.util.Base64;

@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class Login {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public Login(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;

        this.objectMapper = objectMapper;
    }

    @PostMapping("/")
    public ResponseEntity<Object> login(@RequestBody UserPassword userPass) {
        String url = "https://restapi.tu.ac.th/api/v1/auth/Ad/verify";
        String token = "TUa026226293c65b5b8cae84477231b93c83368a7d7e1aebdc2bc885fe4f1209fca3fb9af0d9dfe4710dbc7fcdc0c6ffe4";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Application-Key", token);
        HttpEntity<UserPassword> entity = new HttpEntity<>(userPass, headers);
        try {
            ResponseEntity<Object> res = restTemplate.postForEntity(url, entity, Object.class);

            HttpHeaders headers1 = new HttpHeaders();
            headers1.setContentType(MediaType.APPLICATION_JSON);
            headers1.add("Set-Cookie", createCookie(res));

            return ResponseEntity
                    .status(res.getStatusCode())
                    .headers(headers1)
                    .body(res.getBody());
        } catch (HttpClientErrorException e) {
            String errorResponse = e.getResponseBodyAsString();
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(errorResponse);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while trying to login.");
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // สร้าง Cookie
    private String createCookie(ResponseEntity<Object> res) throws JsonProcessingException {
        String data = objectMapper.writeValueAsString(res.getBody());
        JsonNode root = objectMapper.readTree(data);
        ResponseCookie responseCookie = ResponseCookie.from("cookie", encodeStringToBase64(data))
                .httpOnly(true)
                .path("/")
                .maxAge(30 * 60)
                .build();

        return responseCookie.toString();
    }

    // เข้ารหัสรูปแบบ Base64
    private String encodeStringToBase64(String input) {

        byte[] bytesToEncode = input.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        return Base64.getEncoder().encodeToString(bytesToEncode);
    }

    // ถอดรหัสรูปแบบ Base64
    private String decodeStringFromBase64(String encodedInput) {

        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput);

        return new String(decodedBytes, java.nio.charset.StandardCharsets.UTF_8);
    }

    @GetMapping("/checkCookie")
    public ResponseEntity<Object> checkCookie(HttpServletRequest request) {
        try {
            System.out.println(request.getCookies()[0].getName());
            Cookie[] cookies = request.getCookies();
            for (Cookie i : cookies) {
                if ("cookie".equals(i.getName())) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .headers(headers)
                            .body(objectMapper.readValue(decodeStringFromBase64(i.getValue()), Object.class));
                }
            }
        } catch (Exception e) {
            System.out.println(1316516);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Object() {
                        private final String Message = "EEEEEEEEEEEEEEEE";

                        public String getMessage() {
                            return Message;
                        }
                    });
        }
        return null;
    }
}
