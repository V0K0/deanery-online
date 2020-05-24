package com.vozniuk.springapplication.service.impl;

import com.vozniuk.springapplication.domain.data.university.Student;
import com.vozniuk.springapplication.domain.data.university.Subject;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import com.vozniuk.springapplication.repositories.StudentRepository;
import com.vozniuk.springapplication.service.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addOrUpdateStudent(Student student) {
        studentRepository.saveAndFlush(student);
        return student;
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student getStudentById(Integer id) {
            return studentRepository.findById(id).isPresent() ?  studentRepository.findById(id).get() : null;
    }

    @Override
    public List<Student> getAllStudentsByGroup(UniversityGroup group) {
       return studentRepository.findAllByGroup(group);
    }

    @Override
    public Page<Student> findAllLimit(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Long getStudentsCount(){
        return studentRepository.count();
    }

}
