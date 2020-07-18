package com.vozniuk.deanery.service.services;

import com.vozniuk.deanery.domain.data.university.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {

    Teacher addOrUpdateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    Teacher getTeacherById(Integer id);

    Page<Teacher> findAllLimit(Pageable pageable);

    Long getTeachersCount();

    Teacher getByNameAndLastname(String name, String lastname);

}
