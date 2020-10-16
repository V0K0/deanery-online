package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.data.university.Teacher;
import com.vozniuk.deanery.repository.TeacherRepository;
import com.vozniuk.deanery.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher addOrUpdateTeacher(Teacher teacher) {
        return teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Teacher> findAllLimit(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public Long getTeachersCount() {
        return teacherRepository.count();
    }

    @Override
    public Teacher getByNameAndLastname(String name, String lastname) {
        return teacherRepository.findByLastnameAndName(lastname, name).orElseThrow(EntityNotFoundException::new);
    }
}
