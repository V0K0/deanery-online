package com.vozniuk.deanery.ui.model;

import com.vozniuk.deanery.data.university.Student;
import com.vozniuk.deanery.data.user.User;
import com.vozniuk.deanery.service.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * Loads {@link Student} by {@link User} id in {@link Model}
 */
@Component
public class StudentModelLoader implements ModelLoader<User> {

    private final StudentService studentService;

    public StudentModelLoader(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void load(User user, Model model) {
        Student student = studentService.getStudentById(user.getId());
        model.addAttribute("student", student);
    }
}
