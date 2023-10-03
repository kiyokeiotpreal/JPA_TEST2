package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.TypeCourse;

public interface ITypeCourse {
    public boolean addTypeCourse(TypeCourse typeCourse);
    public boolean reviseTypeCourse(TypeCourse typeCourse);
    public boolean removeTypeCourse(int typeCourseId);
}
