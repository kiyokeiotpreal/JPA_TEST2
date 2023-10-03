package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.TypeCourse;
import com.example.jpa_test2.Repository.TypeCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeCourseService implements ITypeCourse{

    @Autowired
    TypeCourseRepository typeCourseRepo;

    @Override
    public boolean addTypeCourse(TypeCourse typeCourse) {
        if(! typeCourseRepo.existsById(typeCourse.getTypeCourseId())){
            typeCourseRepo.save(typeCourse);
            return true;
        }
        return false;
    }

    @Override
    public boolean reviseTypeCourse(TypeCourse typeCourse) {
        TypeCourse typeCourse1 = typeCourseRepo.findById(typeCourse.getTypeCourseId()).orElse(null);
        if(typeCourse1 != null){
            typeCourse1 = typeCourse;
            typeCourseRepo.save(typeCourse1);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeTypeCourse(int typeCourseId) {
        if(typeCourseRepo.existsById(typeCourseId)){
            typeCourseRepo.deleteById(typeCourseId);
            return true;
        }
        return false;
    }
}
