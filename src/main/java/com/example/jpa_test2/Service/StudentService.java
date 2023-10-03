package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.RegisterCourse;
import com.example.jpa_test2.Model.Student;
import com.example.jpa_test2.Model.TypeCourse;
import com.example.jpa_test2.Repository.RegisterCourseRepository;
import com.example.jpa_test2.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudent{
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private RegisterCourseRepository registerCourseRepo;

    @Override
    public boolean addStudent(Student student) {
        if(! studentRepo.existsById(student.getStudentId())){
            boolean isCheck = true;
            for (Student student1: studentRepo.findAll()) {
                if(student1.getEmail().equals(student.getEmail()) || student1.getPhoneNumber().equals(student.getPhoneNumber())){
                    isCheck = false;
                }
            }
            if(isCheck){
                String name = formatName(student.getName());
                student.setName(name);
                studentRepo.save(student);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean reviseStudent(Student student) {
        Student student1 = studentRepo.findById(student.getStudentId()).orElse(null);
        if(student1 != null){
            boolean isCheck = true;
            for (Student studentCheck: studentRepo.findAll()) {
                if(studentCheck.getStudentId() != student1.getStudentId() && studentCheck.getEmail().equals(student.getEmail())){
                    isCheck = false;
                }
                if(studentCheck.getStudentId() != student1.getStudentId() && studentCheck.getPhoneNumber().equals(student.getPhoneNumber())){
                    isCheck = false;
                }
            }
            if(isCheck){
                student1 = student;
                String name = formatName(student1.getName());
                student1.setName(name);
                studentRepo.save(student1);
                return true;
            }
        }
        return false;
    }

    public String formatName(String str) {
        str = str.trim();
        String[] hoTen = str.split("\\s+");
        String ketQua = "";
        for (int i = 0; i < hoTen.length - 1; i++) {
            ketQua += hoTen[i].substring(0, 1).toUpperCase();
            ketQua += hoTen[i].substring(1).toLowerCase();
            ketQua += " ";
        }
        ketQua += hoTen[hoTen.length-1].substring(0,1).toUpperCase() + hoTen[hoTen.length-1].substring(1).toLowerCase();
        return ketQua;
    }

    @Override
    public boolean deleteStudent(int studentId) {
        if(studentRepo.existsById(studentId)){
            for (RegisterCourse registerCourse: registerCourseRepo.findAll()) {
                if(registerCourse.getStudent().getStudentId()==studentId){
                    registerCourseRepo.delete(registerCourse);
                }
            }
            studentRepo.deleteById(studentId);
            return true;
        }
        return false;
    }

    @Override
    public Page<Student> findStudentByPage(int pagenumber, int limit) {
        Pageable pageable = PageRequest.of(pagenumber-1, limit);
        return studentRepo.findAll(pageable);
    }
}
