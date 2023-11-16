package th.ac.tu.cs.services.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import th.ac.tu.cs.services.model.Form.AddDropFormModel;
import th.ac.tu.cs.services.model.Form.NormalFormModel;

import java.util.List;

public interface NormalRepository {
    List<NormalFormModel> findByStudentID(String studentId);
    List<NormalFormModel> findAllForState(int state);
    int deleteFormById(long id);
    NormalFormModel findByid(long id);
    int updateState(long id, int state);
    int createForm(NormalFormModel form) throws JsonProcessingException;
}
