package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Course;
import com.example.jpa_test2.Model.RegisterCourse;
import com.example.jpa_test2.Model.StudentStatus;
import com.example.jpa_test2.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class RegisterCourseService implements IRegisterCourse {
    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private RegisterCourseRepository registerCourseRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentStatusRepository studentStatusRepo;

    public boolean updateRegisterCourse(RegisterCourse registerCourse) {
        Course course = courseRepo.findById(registerCourse.getCourse().getCourseId()).orElse(null);
        if(course != null){
            registerCourse.setCourse(course);
            LocalDateTime localDateTime = LocalDateTime.now();
            LocalDate regisDate = localDateTime.toLocalDate();
            registerCourse.setRegisterDate(regisDate);
            StudentStatus status = studentStatusRepo.findById(registerCourse.getStatus().getStatusId()).orElse(null);
            if (status != null && status.getStatusName().equals("dang hoc")) {
                LocalDate startDate = registerCourse.getStartDate();
                if(startDate.compareTo(regisDate) >= 0){
                    registerCourse.setStartDate(startDate);
                    LocalDate finishDate = startDate.plusDays(registerCourse.getCourse().getLongCourse());
                    registerCourse.setStartDate(startDate);
                    registerCourse.setFinishDate(finishDate);
                    registerCourseRepo.save(registerCourse);
                    courseService.updateCourse(course);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addRegisterCourse(RegisterCourse regis) {
        if( ! registerCourseRepo.existsById(regis.getId())){
            for (RegisterCourse registerCourse: registerCourseRepo.findAll()) {
                if(registerCourse.getCourse().getCourseId()==regis.getCourse().getCourseId()
                        && registerCourse.getStudent().getStudentId()==regis.getStudent().getStudentId()){
                    return false;
                }
            }
            return updateRegisterCourse(regis);
        }
        return false;
    }

    @Override
    public boolean reviseRegisterCourse(RegisterCourse regis) {
        int regisId = regis.getId();
        RegisterCourse registerCourse = registerCourseRepo.findById(regisId).orElse(null);
        int courseId = registerCourse.getCourse().getCourseId();
        Course courseOld = courseRepo.findById(courseId).orElse(null);
        if(registerCourseRepo.existsById(regisId)){
            if(regis.getStartDate().compareTo(registerCourse.getRegisterDate()) >= 0 && studentRepo.existsById(regis.getStudent().getStudentId())
            && studentStatusRepo.existsById(regis.getStatus().getStatusId()) && accountRepo.existsById(regis.getAccount().getAccountId())){
                LocalDate registerDate = registerCourse.getRegisterDate();
                registerCourse = regis;
                registerCourse.setRegisterDate(registerDate);
                Course courseNew = courseRepo.findById(regis.getCourse().getCourseId()).orElse(null);
                registerCourse.setCourse(courseNew);
                LocalDate finishDate = registerCourse.getStartDate().plusDays(registerCourse.getCourse().getLongCourse());
                registerCourse.setFinishDate(finishDate);
                registerCourseRepo.save(registerCourse);
                if(courseNew != null ){
                    courseService.updateCourse(courseOld);
                    courseService.updateCourse(courseNew);
                }
                if(registerCourse.getStatus().getStatusId() != 1){
                    registerCourseRepo.delete(registerCourse);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteRegisterCourse(int regisId) {
        return false;
    }

    @Override
    public Page<RegisterCourse> findRegisterCourseBypage(int pageNumber, int limit) {
        Pageable pageable = PageRequest.of(pageNumber-1, limit);
        return  registerCourseRepo.findAll(pageable);
    }
}
