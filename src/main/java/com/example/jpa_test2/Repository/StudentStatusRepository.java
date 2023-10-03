package com.example.jpa_test2.Repository;

import com.example.jpa_test2.Model.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentStatusRepository extends JpaRepository<StudentStatus, Integer> {
}
