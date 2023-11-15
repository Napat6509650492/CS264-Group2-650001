package th.ac.tu.cs.services.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.ac.tu.cs.services.model.Form.AddDropFormModel;
import th.ac.tu.cs.services.model.Form.MessageModel;
import th.ac.tu.cs.services.model.Form.SubjectModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class JdbcAddDropRepository implements AddDropRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<AddDropFormModel> findByStudentID(String studentId) {
        String sql = "SELECT * FROM adddropform WHERE studentId = ?";
        List<AddDropFormModel> data = jdbcTemplate.query(sql,new Object[]{studentId},new AddDropFormRowMapper());
        return data;
    }

    @Override
    public List<AddDropFormModel> findAllForType(String type) {
        return null;
    }

    @Override
    public int deleteFormById(long id) {
        return 0;
    }

    @Override
    public AddDropFormModel findByid(long id) {
        return null;
    }

    @Override
    public int createForm(AddDropFormModel form){
        form.setPhone("4545665");
        form.setMobilePhone("87789879789");
        String sql = "INSERT INTO addDropForm (topic ,date, too, addordrop, title, studentFirstName, " +
                "studentLastName, studentId, studentYear, studyField, advisor, addressNumber, moo, " +
                "tumbol, amphur, province, postalCode, mobilePhone, phone, cause, subject , state,message) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try {
            jdbcTemplate.update(sql,
                    form.getTopic(),
                    form.getDate(),
                    form.getTo(),
                    form.getAddordrop(),
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
                    form.getCause(),
                    mapper.writeValueAsString(form.getSubject()),
                    1,
                    mapper.writeValueAsString(new MessageModel())
                    );
            return 0;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return 1;
        }
    }
}

class AddDropFormRowMapper implements RowMapper<AddDropFormModel> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public AddDropFormModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        AddDropFormModel form = new AddDropFormModel();
        form.setId(rs.getLong("id"));
        form.setDate(rs.getDate("date")); // ต้องแปลง Date ถ้ามีรูปแบบพิเศษ
        form.setAdd(rs.getBoolean("addordrop"));
        form.setTitle(rs.getString("title"));
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
        form.setCause(rs.getString("cause"));
        form.setState(rs.getInt("state"));
        try {
            form.setSubject(mapper.readValue(rs.getString("subject"), SubjectModel.class));
            form.setMessage(mapper.readValue(rs.getString("message"), MessageModel.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return form;
    }
}