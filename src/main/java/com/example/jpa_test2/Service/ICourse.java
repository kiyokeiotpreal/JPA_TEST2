package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Article;
import com.example.jpa_test2.Model.Course;
import com.example.jpa_test2.Model.RegisterCourse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICourse {
    public boolean addCourse(Course course);
    public boolean reviseCourse(Course course);
    public boolean removeCourse(int courseId);
    public List<Course> findCourse(String courseName);
    public void updateCourse(Course course);
    public Page<Course> displayArticleByPage(int numberPage, int limit);
}
