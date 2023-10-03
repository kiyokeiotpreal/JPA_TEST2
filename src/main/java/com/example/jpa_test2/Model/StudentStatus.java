package com.example.jpa_test2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class StudentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;

    private String statusName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    @JsonBackReference
    private Set<RegisterCourse> registerCourses;

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Set<RegisterCourse> getRegisterCourses() {
        return registerCourses;
    }

    public void setRegisterCourses(Set<RegisterCourse> registerCourses) {
        this.registerCourses = registerCourses;
    }
}
