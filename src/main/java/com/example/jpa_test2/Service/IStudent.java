package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Student;
import org.springframework.data.domain.Page;

public interface IStudent {
    public boolean addStudent(Student student);
    public boolean reviseStudent(Student student);
    public boolean deleteStudent(int studentId);
    Page<Student> findStudentByPage(int pagenumber, int limit);
}
