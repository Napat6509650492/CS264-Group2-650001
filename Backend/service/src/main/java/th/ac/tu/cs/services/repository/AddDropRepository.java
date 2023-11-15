package th.ac.tu.cs.services.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import th.ac.tu.cs.services.model.Form.AddDropFormModel;

import java.util.List;

public interface AddDropRepository {
    List<AddDropFormModel> findByStudentID(String studentId);
    List<AddDropFormModel> findAllForType(String type);
    int deleteFormById(long id);
    AddDropFormModel findByid(long id);
    int createForm(AddDropFormModel form) throws JsonProcessingException;
}
