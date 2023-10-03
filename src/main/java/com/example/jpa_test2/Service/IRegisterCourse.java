package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.RegisterCourse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRegisterCourse {
    public boolean addRegisterCourse(RegisterCourse regis);
    public boolean reviseRegisterCourse( RegisterCourse regis);
    public boolean deleteRegisterCourse( int regisId);
    public Page<RegisterCourse> findRegisterCourseBypage(int pageNumber, int limit);
}
