package com.vozniuk.springapplication.service.impl;

import com.vozniuk.springapplication.domain.data.university.Teacher;
import com.vozniuk.springapplication.repositories.TeacherRepository;
import com.vozniuk.springapplication.service.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl  implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher addOrUpdateTeacher(Teacher teacher) {
        teacherRepository.saveAndFlush(teacher);
        return teacher;
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherRepository.deleteById(teacher.getTeacherId());
    }

    @Override
    public Teacher getTeacherById(Integer id) {
       return teacherRepository.findById(id).orElse(null);
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
        return teacherRepository.findByLastnameAndName(lastname, name);
    }

}
