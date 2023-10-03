package com.example.jpa_test2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.awt.*;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;

    private int longCourse;

    private String introduce;

    private String content;

    private double fee;

    private long numStudent;

    private long numCourse;

    private byte[] image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeCourseId", foreignKey = @ForeignKey(name = "fk_TypeCourse_Course"))
    @JsonManagedReference
    private TypeCourse typecourse;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    @JsonBackReference
    private Set<RegisterCourse> registerCourseSet;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getLongCourse() {
        return longCourse;
    }

    public void setLongCourse(int longCourse) {
        this.longCourse = longCourse;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public long getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(long numStudent) {
        this.numStudent = numStudent;
    }

    public long getNumCourse() {
        return numCourse;
    }

    public void setNumCourse(long numCourse) {
        this.numCourse = numCourse;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public TypeCourse getTypecourse() {
        return typecourse;
    }

    public void setTypecourse(TypeCourse typecourse) {
        this.typecourse = typecourse;
    }

    public Set<RegisterCourse> getRegisterCourseSet() {
        return registerCourseSet;
    }

    public void setRegisterCourseSet(Set<RegisterCourse> registerCourseSet) {
        this.registerCourseSet = registerCourseSet;
    }
}
