package th.ac.tu.cs.services.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import th.ac.tu.cs.services.model.Form.AddDropFormModel;
import th.ac.tu.cs.services.model.Form.SubjectModel;
import th.ac.tu.cs.services.repository.JdbcAddDropRepository;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/adddrop")
public class AddDropController {
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private JdbcAddDropRepository jdbcAddDropForm; // สมมติว่าคุณมี service ที่เรียกใช้งานตามข้างล่าง

    @PostMapping("create")
    public ResponseEntity<?> createForm(@ModelAttribute AddDropFormModel form ,@RequestParam("subject") String subject) {
        try {
            form.setSubject(mapper.readValue(subject, SubjectModel.class));
            int result = jdbcAddDropForm.createForm(form);
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
            AddDropFormModel form = jdbcAddDropForm.findByid(id);
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
        return ResponseEntity.ok(jdbcAddDropForm.findByStudentID(studentId));
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<?> getFormsByState(@PathVariable int state) {
        return ResponseEntity.ok(jdbcAddDropForm.findAllForState(state));
    }

    @PutMapping("/{id}/state")
    public ResponseEntity<?> updateFormState(@PathVariable long id, @RequestBody int state) {
        int result = jdbcAddDropForm.updateState(id, state);
        if (result == 1) {
            return ResponseEntity.ok("State updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Error updating state.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteForm(@PathVariable long id) {
        int result = jdbcAddDropForm.deleteFormById(id);
        if (result == 1) {
            return ResponseEntity.ok("Form deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Error deleting form.");
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("subject"); // fields ที่ไม่อนุญาต
    }
}
