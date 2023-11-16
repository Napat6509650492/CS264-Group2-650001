package th.ac.tu.cs.services.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.ac.tu.cs.services.model.Form.MessageModel;
import th.ac.tu.cs.services.model.Form.NormalFormModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcNormalRepository implements NormalRepository{
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<NormalFormModel> findByStudentID(String studentId) {
        String sql = "SELECT * FROM normalForm WHERE studentId = ?";
        return jdbcTemplate.query(sql,new NormalRowMapper(),studentId);
    }

    @Override
    public List<NormalFormModel> findAllForState(int state) {
        String sql = "SELECT * FROM normalForm WHERE state = ?";
        return jdbcTemplate.query(sql,new NormalRowMapper(),state);
    }

    @Override
    public int deleteFormById(long id) {
        String sql = "DELETE FROM normalForm WHERE id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public NormalFormModel findByid(long id) {
        String sql = "SELECT * FROM normalForm WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,new NormalRowMapper(),id);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateState(long id, int state) {
        String sql = "UPDATE normalForm SET state = ? WHERE id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int createForm(NormalFormModel form) throws JsonProcessingException {
        String sql = "INSERT INTO addDropForm (topic ,date, too, title, studentFirstName, " +
                "studentLastName, studentId, studentYear, studyField, advisor, addressNumber, moo, " +
                "tumbol, amphur, province, postalCode, mobilePhone, phone, objective, cause , state, message) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try {
            jdbcTemplate.update(sql,
                    form.getTopic(),
                    form.getDate(),
                    form.getToo(),
                    form.getTitle(),
                    form.getStudentFirstName(),
                    form.getStudentLastName(),
                    form.getStudentId(),
                    form.getStudentYear(),
                    form.getStudyField(),
                    form.getAdvisor(),
                    form.getAddressNumber(),
                    form.getMoo(),
                    form.getTumbol(),
                    form.getAmphur(),
                    form.getProvince(),
                    form.getPostalCode(),
                    form.getMobilePhone(),
                    form.getPhone(),
                    form.getObjective(),
                    form.getCause(),
                    1,
                    mapper.writeValueAsString(new MessageModel())
            );
            return 1;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}

class NormalRowMapper implements RowMapper<NormalFormModel> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public NormalFormModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        NormalFormModel form = new NormalFormModel();
        form.setId(rs.getLong("id"));
        form.setDate(rs.getDate("date")); // ต้องแปลง Date ถ้ามีรูปแบบพิเศษ
        form.setTitle(rs.getString("title"));
        form.setToo(rs.getString("too"));
        form.setStudentFirstName(rs.getString("studentFirstName"));
        form.setStudentLastName(rs.getString("studentLastName"));
        form.setStudentId(rs.getString("studentId"));
        form.setStudentYear(rs.getString("studentYear"));
        form.setStudyField(rs.getString("studyField"));
        form.setAdvisor(rs.getString("advisor"));
        form.setAddressNumber(rs.getString("addressNumber"));
        form.setMoo(rs.getString("moo"));
        form.setTumbol(rs.getString("tumbol"));
        form.setAmphur(rs.getString("amphur"));
        form.setProvince(rs.getString("province"));
        form.setPostalCode(rs.getString("postalCode"));
        form.setMobilePhone(rs.getString("mobilePhone"));
        form.setPhone(rs.getString("phone"));
        form.setObjective(rs.getString("objective"));
        form.setCause(rs.getString("cause"));
        form.setState(rs.getInt("state"));
        try {
            form.setMessage(mapper.readValue(rs.getString("message"), MessageModel.class));
        } catch (Exception e) {
            System.out.println("AddDropRowmapper Error");
            form.setMessage(null);
        }


        return form;
    }
}