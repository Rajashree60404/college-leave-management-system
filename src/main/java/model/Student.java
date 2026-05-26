package model;

public class Student {

    
    
    private String studentId;
    private String studentName;
    private String password;
    private String email;


    
    
    // =============================
    // Default Constructor
    // =============================

    public Student() {

    }
 // Getter
    public String getStudentId() {
        return studentId;
    }

    // Setter
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // Name Getter
    public String getStudentName() {
        return studentName;
    }

    // Name Setter
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    // Password Getter
    public String getPassword() {
        return password;
    }

    // Password Setter
    public void setPassword(String password) {
        this.password = password;
    }

    // Email Getter
    public String getEmail() {
        return email;
    }

    // Email Setter
    public void setEmail(String email) {
        this.email = email;
    }


}