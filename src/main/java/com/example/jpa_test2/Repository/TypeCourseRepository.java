package com.example.jpa_test2.Repository;

import com.example.jpa_test2.Model.TypeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCourseRepository extends JpaRepository<TypeCourse, Integer> {
}
