package com.vozniuk.deanery.service.services;

import com.vozniuk.deanery.domain.data.university.Student;
import com.vozniuk.deanery.domain.data.university.UniversityGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    Student addOrUpdateStudent(Student student);

    void deleteStudent(Student student);

    Student getStudentById(Integer id);

    List<Student> getAllStudentsByGroup(UniversityGroup group);

    Page<Student> findAllLimit(Pageable pageable);

    Long getStudentsCount();

}
