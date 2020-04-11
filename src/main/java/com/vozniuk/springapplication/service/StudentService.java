package com.vozniuk.springapplication.service;

import com.vozniuk.springapplication.domain.data.university.Student;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    void deleteStudent(Student student);

    Student getStudentById(Integer id);

    Student editStudent(Student student);

    List<Student> getAllStudentsByGroup(UniversityGroup group);

}
