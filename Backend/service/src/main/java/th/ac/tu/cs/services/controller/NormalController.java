package th.ac.tu.cs.services.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import th.ac.tu.cs.services.model.Form.AddDropFormModel;
import th.ac.tu.cs.services.model.Form.NormalFormModel;
import th.ac.tu.cs.services.repository.JdbcNormalRepository;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/normal")
public class NormalController {
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private JdbcNormalRepository jdbcNormalRepository; // สมมติว่าคุณมี service ที่เรียกใช้งานตามข้างล่าง

    @PostMapping("create")
    public ResponseEntity<?> createForm(@ModelAttribute NormalFormModel form) {
        try {
            System.out.println(form.getObjective());
            int result = jdbcNormalRepository.createForm(form);
            if (result == 1) {
                return ResponseEntity.ok("Form created successfully.");
            } else {
                return ResponseEntity.badRequest().body("Error creating form.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFormById(@PathVariable long id) {
        try {
            NormalFormModel form = jdbcNormalRepository.findByid(id);
            if (form != null) {
                return ResponseEntity.ok(form);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getFormsByStudentId(@PathVariable String studentId) {
        return ResponseEntity.ok(jdbcNormalRepository.findByStudentID(studentId));
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<?> getFormsByState(@PathVariable int state) {
        return ResponseEntity.ok(jdbcNormalRepository.findAllForState(state));
    }

    @PutMapping("/{id}/state")
    public ResponseEntity<?> updateFormState(@PathVariable long id, @RequestBody int state) {
        int result = jdbcNormalRepository.updateState(id, state);
        if (result == 1) {
            return ResponseEntity.ok("State updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Error updating state.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteForm(@PathVariable long id) {
        int result = jdbcNormalRepository.deleteFormById(id);
        if (result == 1) {
            return ResponseEntity.ok("Form deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Error deleting form.");
        }
    }
}
