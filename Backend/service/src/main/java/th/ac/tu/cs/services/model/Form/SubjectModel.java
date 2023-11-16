package th.ac.tu.cs.services.model.Form;

public class SubjectModel {
    private String subjectCode;
    private String subjectName;
    private String subjectSection;
    private String subjectDate;
    private int subjectCredit;
    private String subjectTeacher;

    public SubjectModel() {
    }

    public SubjectModel(String subjectCode, String subjectName, String subjectSection, String subjectDate, int subjectCredit, String subjectTeacher) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectSection = subjectSection;
        this.subjectDate = subjectDate;
        this.subjectCredit = subjectCredit;
        this.subjectTeacher = subjectTeacher;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectSection() {
        return subjectSection;
    }

    public void setSubjectSection(String subjectSection) {
        this.subjectSection = subjectSection;
    }

    public String getSubjectDate() {
        return subjectDate;
    }

    public void setSubjectDate(String subjectDate) {
        this.subjectDate = subjectDate;
    }

    public int getSubjectCredit() {
        return subjectCredit;
    }

    public void setSubjectCredit(int subjectCredit) {
        this.subjectCredit = subjectCredit;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }
}


