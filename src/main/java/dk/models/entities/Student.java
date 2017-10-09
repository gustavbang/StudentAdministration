package dk.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern =   "yyyy-MM-dd")
    private Date enrollmentDate;
    private String password;
    private String cpr;

    public Student() {
    }


    public Student(int studentId, String firstName, String lastName, Date enrollmentDate, String password, String cpr) {
        this.id = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrollmentDate = enrollmentDate;
        this.password = password;

        this.cpr = cpr;
    }

    public int getStudentId() {
        return id;
    }

    public void setStudentId(int studentId) {
        this.id = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

