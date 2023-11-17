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
import th.ac.tu.cs.services.model.Form.MessageModel;
import th.ac.tu.cs.services.model.Form.NormalFormModel;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO normalForm (topic ,date, too, title, studentFirstName, " +
                "studentLastName, studentId, studentYear, studyField, advisor, addressNumber, moo, " +
                "tumbol, amphur, province, postalCode, mobilePhone, phone, objective, cause , state, message) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, form.getTopic());
                        ps.setDate(2, new java.sql.Date(form.getDate().getTime()));
                        ps.setString(3, form.getToo());
                        ps.setString(4, form.getTitle());
                        ps.setString(5, form.getStudentFirstName());
                        ps.setString(6, form.getStudentLastName());
                        ps.setString(7, form.getStudentId());
                        ps.setString(8, form.getStudentYear());
                        ps.setString(9, form.getStudyField());
                        ps.setString(10, form.getAdvisor());
                        ps.setString(11, form.getAddressNumber());
                        ps.setString(12, form.getMoo());
                        ps.setString(13, form.getTumbol());
                        ps.setString(14, form.getAmphur());
                        ps.setString(15, form.getProvince());
                        ps.setString(16, form.getPostalCode());
                        ps.setString(17, form.getMobilePhone());
                        ps.setString(18, form.getPhone());
                        ps.setString(19, form.getObjective());
                        ps.setString(20, form.getCause());
                        ps.setInt(21, 1); // Assuming this is an integer
                        try {
                            ps.setString(22, mapper.writeValueAsString(new MessageModel()));
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
        String sql = "INSERT INTO normalFiles (form_id, file_data,file_name ,file_type, file_size) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,id,file.getBytes(),file.getOriginalFilename(),file.getContentType(),file.getSize());
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