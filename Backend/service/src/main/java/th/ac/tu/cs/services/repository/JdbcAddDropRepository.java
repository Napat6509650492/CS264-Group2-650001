package th.ac.tu.cs.services.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import th.ac.tu.cs.services.model.Form.AddDropFormModel;
import th.ac.tu.cs.services.model.Form.MessageModel;
import th.ac.tu.cs.services.model.Form.SubjectModel;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;


@Repository
public class JdbcAddDropRepository implements AddDropRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<AddDropFormModel> findByStudentID(String studentId) {
        String sql = "SELECT * FROM adddropform WHERE studentId = ?";
        return jdbcTemplate.query(sql,new AddDropFormRowMapper(),studentId);
    }

    @Override
    public List<AddDropFormModel> findAllForState(int state) {
        String sql = "SELECT * FROM adddropform WHERE state = ?";
        return jdbcTemplate.query(sql,new AddDropFormRowMapper(),state);
    }

    @Override
    public int deleteFormById(long id) {
        String sql = "DELETE FROM addDropForm WHERE id = ?";
        String sqldeletefiles = "DELETE FROM addDropFiles WHERE form_id = ?";
        jdbcTemplate.update(sqldeletefiles,id);
            return jdbcTemplate.update(sql,id);
    }

    @Override
    public AddDropFormModel findByid(long id) {
        String sql = "SELECT * FROM addDropForm WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,new AddDropFormRowMapper(),id);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int updateState(long id, int state){
        String sql = "UPDATE addDropForm SET state = ? WHERE id = ?";
        return jdbcTemplate.update(sql,state,id);
    }
    @Override
    public int createForm(AddDropFormModel form){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO addDropForm (topic ,date, too, addordrop, title, studentFirstName, " +
                "studentLastName, studentId, studentYear, studyField, advisor, addressNumber, moo, " +
                "tumbol, amphur, province, postalCode, mobilePhone, phone, cause, subject , state,message) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try {
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, form.getTopic());
                        ps.setDate(2, new java.sql.Date(form.getDate().getTime()));
                        ps.setString(3, form.getToo());
                        ps.setString(4, form.getAddordrop());
                        ps.setString(5, form.getTitle());
                        ps.setString(6, form.getStudentFirstName());
                        ps.setString(7, form.getStudentLastName());
                        ps.setString(8, form.getStudentId());
                        ps.setString(9, form.getStudentYear());
                        ps.setString(10, form.getStudyField());
                        ps.setString(11, form.getAdvisor());
                        ps.setString(12, form.getAddressNumber());
                        ps.setString(13, form.getMoo());
                        ps.setString(14, form.getTumbol());
                        ps.setString(15, form.getAmphur());
                        ps.setString(16, form.getProvince());
                        ps.setString(17, form.getPostalCode());
                        ps.setString(18, form.getMobilePhone());
                        ps.setString(19, form.getPhone());
                        ps.setString(20, form.getCause());
                        try {
                            ps.setString(21, mapper.writeValueAsString(form.getSubject()));
                        } catch (JsonProcessingException e) {
                            System.out.println("map subject error");
                        }
                        ps.setInt(22, 1); // Assuming this is an integer
                        try {
                            ps.setString(23, mapper.writeValueAsString(new MessageModel()));
                        } catch (JsonProcessingException e) {
                            System.out.println("map messgae error");
                        }
                        return ps;
                    },
                    keyHolder
            );
            if(form.getFiles() != null){
                form.getFiles().forEach(item->{
                    try {
                        storePdfFile(item,keyHolder.getKey().longValue());
                    } catch (IOException e) {
                        System.out.println("Store File Error");
                    }
                });
            }
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    private void storePdfFile(MultipartFile file , long id) throws IOException {
        String sql = "INSERT INTO addDropFiles (form_id, file_data, file_name,file_type, file_size) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,id,file.getBytes(),file.getOriginalFilename(),file.getContentType(),file.getSize());
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
        form.setToo(rs.getString("too"));
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
        } catch (Exception e) {
            System.out.println("AddDropRowmapper Error");
            form.setSubject(null);
        }
        try {
            form.setMessage(mapper.readValue(rs.getString("message"), MessageModel.class));
        } catch (Exception e) {
            System.out.println("AddDropRowmapper Error");
            form.setMessage(null);
        }


        return form;
    }
}