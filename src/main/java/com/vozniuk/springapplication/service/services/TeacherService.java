package com.vozniuk.springapplication.service.services;

import com.vozniuk.springapplication.domain.data.university.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    Teacher addOrUpdateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    Teacher getTeacherById(Integer id);

    Page<Teacher> findAllLimit(Pageable pageable);

    Long getTeachersCount();

    Teacher getByNameAndLastname(String name, String lastname);

}
