package com.example.jpa_test2.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class RegisterCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private LocalDate registerDate;

    private LocalDate startDate;

    private LocalDate finishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId", foreignKey = @ForeignKey(name = "fk_Student_RegisterCourse"))
    @JsonManagedReference
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId", foreignKey = @ForeignKey(name = "fk_Course_RegisterCourse"))
    @JsonManagedReference
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId", foreignKey = @ForeignKey(name = "fk_StudentStatus_RegisterCourse"))
    @JsonManagedReference
    private StudentStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId", foreignKey = @ForeignKey(name = "fk_Account_RegisterCourse"))
    @JsonManagedReference
    private Account account;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
