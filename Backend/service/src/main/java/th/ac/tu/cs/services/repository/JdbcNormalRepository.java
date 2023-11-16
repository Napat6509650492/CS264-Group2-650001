package th.ac.tu.cs.services.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import th.ac.tu.cs.services.model.Form.NormalFormModel;

import java.util.List;

public class JdbcNormalRepository implements NormalRepository{

    @Override
    public List<NormalFormModel> findByStudentID(String studentId) {
        String sql = "SELECT * FROM normalForm WHERE studentId = ?";
        return null;
    }

    @Override
    public List<NormalFormModel> findAllForState(int state) {
        String sql = "SELECT * FROM normalForm WHERE state = ?";
        return null;
    }

    @Override
    public int deleteFormById(long id) {
        String sql = "DELETE FROM normalForm WHERE id = ?";
        return 0;
    }

    @Override
    public NormalFormModel findByid(long id) {
        String sql = "SELECT * FROM normalForm WHERE id = ?";
        return null;
    }

    @Override
    public int updateState(long id, int state) {
        String sql = "UPDATE normalForm SET state = ? WHERE id = ?";
        return 0;
    }

    @Override
    public int createForm(NormalFormModel form) throws JsonProcessingException {
        String sql = "INSERT INTO addDropForm (topic ,date, too, title, studentFirstName, " +
                "studentLastName, studentId, studentYear, studyField, advisor, addressNumber, moo, " +
                "tumbol, amphur, province, postalCode, mobilePhone, phone, objective, cause , state, message) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        return 0;
    }
}
