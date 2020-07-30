package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.domain.data.university.Teacher;
import com.vozniuk.deanery.repositories.TeacherRepository;
import com.vozniuk.deanery.service.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl  implements TeacherService{

    private TeacherRepository teacherRepository;

    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher addOrUpdateTeacher(Teacher teacher) {
        if (isNameLastnameFilled(teacher)){
            teacherRepository.saveAndFlush(teacher);
            return teacher;
        }
        return null;
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
        return teacherRepository.getTeachersCount();
    }

    @Override
    public Teacher getByNameAndLastname(String name, String lastname) {
        return teacherRepository.findByLastnameAndName(lastname, name);
    }

    private boolean isNameLastnameFilled(Teacher teacher) {
        return !teacher.getName().isBlank() && !teacher.getLastname().isBlank();
    }

}
