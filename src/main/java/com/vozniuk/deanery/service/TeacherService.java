package com.vozniuk.deanery.service;

import com.vozniuk.deanery.data.university.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {

    Teacher addOrUpdateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    Teacher getTeacherById(Long id);

    Page<Teacher> findAllLimit(Pageable pageable);

    Long getTeachersCount();

    Teacher getByNameAndLastname(String name, String lastname);

}
