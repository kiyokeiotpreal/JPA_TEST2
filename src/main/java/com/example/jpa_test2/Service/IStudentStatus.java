package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.StudentStatus;

public interface IStudentStatus {
    public boolean addStatus(StudentStatus status);
    public boolean reviseStatus(StudentStatus status);
    public boolean deleteStatus(int statusId);
}
