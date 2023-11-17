package th.ac.tu.cs.services.model.Form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

public class AddDropFormModel {

    private Long id;
    private Date date;
    private boolean add;
    private String title;
    private String topic;
    private String too;
    private String addordrop;
    private String studentFirstName;
    private String studentLastName;
    private String studentId;
    private String studentYear;
    private String studyField;
    private String advisor;
    private String addressNumber;
    private String moo;
    private String tumbol;
    private String amphur;
    private String province;
    private String postalCode;
    private String mobilePhone;
    private String phone;
    private String cause;
    private SubjectModel subject;
    private int state;
    private MessageModel message;
    private List<MultipartFile> files;

    public AddDropFormModel() {
    }

    public AddDropFormModel(Long id, Date date, boolean add, String title, String topic, String too, String addordrop, String studentFirstName, String studentLastName, String studentId, String studentYear, String studyField, String advisor, String addressNumber, String moo, String tumbol, String amphur, String province, String postalCode, String mobilePhone, String phone, String cause, SubjectModel subject, int state, MessageModel message, List<MultipartFile> files) {
        this.id = id;
        this.date = date;
        this.add = add;
        this.title = title;
        this.topic = topic;
        this.too = too;
        this.addordrop = addordrop;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentId = studentId;
        this.studentYear = studentYear;
        this.studyField = studyField;
        this.advisor = advisor;
        this.addressNumber = addressNumber;
        this.moo = moo;
        this.tumbol = tumbol;
        this.amphur = amphur;
        this.province = province;
        this.postalCode = postalCode;
        this.mobilePhone = mobilePhone;
        this.phone = phone;
        this.cause = cause;
        this.subject = subject;
        this.state = state;
        this.message = message;
        this.files = files;
    }

    public String getAddordrop() {
        return addordrop;
    }

    public void setAddordrop(String addordrop) {
        this.addordrop = addordrop;
    }

    public String getToo() {
        return too;
    }

    public void setToo(String too) {
        this.too = too;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }

    public String getStudyField() {
        return studyField;
    }

    public void setStudyField(String studyField) {
        this.studyField = studyField;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getMoo() {
        return moo;
    }

    public void setMoo(String moo) {
        this.moo = moo;
    }

    public String getTumbol() {
        return tumbol;
    }

    public void setTumbol(String tumbol) {
        this.tumbol = tumbol;
    }

    public String getAmphur() {
        return amphur;
    }

    public void setAmphur(String amphur) {
        this.amphur = amphur;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public MessageModel getMessage() {
        return message;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }


}

