package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.RegisterCourse;
import com.example.jpa_test2.Model.StudentStatus;
import com.example.jpa_test2.Repository.RegisterCourseRepository;
import com.example.jpa_test2.Repository.StudentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentStatusService implements IStudentStatus{

    @Autowired
    private StudentStatusRepository studentStatusRepo;

    @Autowired
    private RegisterCourseRepository registerCourseRepo;

    @Override
    public boolean addStatus(StudentStatus status) {
        if(! studentStatusRepo.existsById(status.getStatusId())){
            studentStatusRepo.save(status);
            return true;
        }
        return false;
    }

    @Override
    public boolean reviseStatus(StudentStatus status) {
        StudentStatus studentStatus = studentStatusRepo.findById(status.getStatusId()).orElse(null);
        if(studentStatus != null){
            studentStatus = status;
            studentStatusRepo.save(studentStatus);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStatus(int statusId) {
        if(studentStatusRepo.existsById(statusId)){
            for (RegisterCourse regis: registerCourseRepo.findAll()) {
                if(regis.getStatus().getStatusId()==statusId){
                    registerCourseRepo.delete(regis);
                }
            }
            studentStatusRepo.deleteById(statusId);
            return true;
        }
        return false;
    }
}
