package com.example.jpa_test2.Repository;

import com.example.jpa_test2.Model.RegisterCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterCourseRepository extends JpaRepository<RegisterCourse, Integer> {
}
