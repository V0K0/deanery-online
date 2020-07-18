package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.domain.data.university.Student;
import com.vozniuk.deanery.domain.data.university.UniversityGroup;
import com.vozniuk.deanery.repositories.StudentRepository;
import com.vozniuk.deanery.service.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

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
    @Transactional(readOnly = true)
    public Student getStudentById(Integer id) {
            return studentRepository.findById(id).isPresent() ?  studentRepository.findById(id).get() : null;
    }

    @Override
    public List<Student> getAllStudentsByGroup(UniversityGroup group) {
       return studentRepository.findAllByStudentGroup(group);
    }

    @Override
    public Page<Student> findAllLimit(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Long getStudentsCount(){
        return studentRepository.getStudentsCount();
    }

}
