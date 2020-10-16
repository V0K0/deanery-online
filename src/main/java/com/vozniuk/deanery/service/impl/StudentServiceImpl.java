package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.data.university.Student;
import com.vozniuk.deanery.data.university.UniversityGroup;
import com.vozniuk.deanery.repository.StudentRepository;
import com.vozniuk.deanery.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addOrUpdateStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Student> getAllStudentsByGroup(UniversityGroup group) {
        return studentRepository.findAllByStudentGroup(group).orElse(new ArrayList<>());
    }

    @Override
    public Page<Student> findAllLimit(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Long getStudentsCount() {
        return studentRepository.count();
    }
}
