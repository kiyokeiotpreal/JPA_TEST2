package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Course;
import com.example.jpa_test2.Model.RegisterCourse;
import com.example.jpa_test2.Model.TypeCourse;
import com.example.jpa_test2.Repository.CourseRepository;
import com.example.jpa_test2.Repository.RegisterCourseRepository;
import com.example.jpa_test2.Repository.StudentStatusRepository;
import com.example.jpa_test2.Repository.TypeCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourse{
    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private TypeCourseRepository typeCourseRepo;

    @Autowired
    private RegisterCourseRepository registerCourseRepo;

    @Autowired
    private StudentStatusRepository studentStatusRepo;


    @Override
    public boolean addCourse(Course course) {
        if(! courseRepo.existsById(course.getCourseId())){
            if(typeCourseRepo.existsById(course.getTypecourse().getTypeCourseId())){
                courseRepo.save(course);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean reviseCourse(Course course) {
        Course newCourse = courseRepo.findById(course.getCourseId()).orElse(null);
        if(newCourse != null){
            newCourse = course;
            TypeCourse newTypeCourse = typeCourseRepo.findById(course.getTypecourse().getTypeCourseId()).orElse(null);
            if(newTypeCourse != null){
                newCourse.setTypecourse(newTypeCourse);
                courseRepo.save(newCourse);
                for (RegisterCourse registerCourse:registerCourseRepo.findAll()) {
                    if(registerCourse.getCourse().getCourseId()== newCourse.getCourseId()){
                        registerCourse.setCourse(newCourse);
                        registerCourseRepo.save(registerCourse);
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeCourse(int courseId) {
        if(courseRepo.existsById(courseId)){
            for (RegisterCourse registerCourse: registerCourseRepo.findAll()) {
                if(registerCourse.getCourse().getCourseId()==courseId){
                    registerCourseRepo.delete(registerCourse);
                }
            }
            courseRepo.deleteById(courseId);
            return  true;
        }
        return false;
    }

    @Override
    public List<Course> findCourse(String courseName) {
        List<Course> resultList = new ArrayList<>();
        for (Course course: courseRepo.findAll()) {
            if(course.getCourseName().toLowerCase().equals(courseName.toLowerCase())){
                resultList.add(course);
            }
        }
        return resultList;
    }

    @Override
    public void updateCourse(Course course) {
            int count = 0;
            for (RegisterCourse regis: registerCourseRepo.findAll()) {
                if(course.getCourseId()==regis.getCourse().getCourseId() && regis.getStatus().getStatusName().equals("dang hoc")){
                    count ++;
                }
            }
            course.setNumStudent(count);
            courseRepo.save(course);
    }

    @Override
    public Page<Course> displayArticleByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return courseRepo.findAll(pageable);
    }
}
