package student_management_system.Class;

public class Course_data {
    private int teacherID;
    private int classID;
    private String subject;
    private int level;
    private String fullName;
    private String start;
    private String end;
    
    // Constructor with all fields
    public Course_data(int teacherID, int classID, String subject,int level, String fullName, String start, String end) {
        this.teacherID = teacherID;
        this.classID = classID;
        this.subject = subject;
        this.level=level;
        this.fullName = fullName;
        this.start = start;
        this.end = end;
    }

    // Constructor with fewer fields (may be used in certain cases)
    public Course_data(int teacherID, int classID, String subject,int level, String fullName) {
        this.teacherID = teacherID;
        this.classID = classID;
        this.subject = subject;
        this.level=level;
        this.fullName = fullName;
        // Optionally, initialize start and end with default values or null
        this.start = "";  // Or null
        this.end = "";    // Or null
    }

    // Getters and Setters
    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level=level;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
