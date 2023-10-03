package com.example.jpa_test2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class TypeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeCourseId;

    private String typeCourseName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typecourse")
    @JsonBackReference
    private Set<Course> courseSet ;

    public int getTypeCourseId() {
        return typeCourseId;
    }

    public void setTypeCourseId(int typeCourseId) {
        this.typeCourseId = typeCourseId;
    }

    public String getTypeCourseName() {
        return typeCourseName;
    }

    public void setTypeCourseName(String typeCourseName) {
        this.typeCourseName = typeCourseName;
    }

    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }
}
