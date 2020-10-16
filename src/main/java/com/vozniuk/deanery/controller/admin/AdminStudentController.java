package com.vozniuk.deanery.controller.admin;

import com.vozniuk.deanery.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin-page")
public class AdminStudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentServiceImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/students")
    public String studentsPage(Model model) {
        Long countOfStudents = studentService.getStudentsCount();
        model.addAttribute("countOfStudents", countOfStudents);
        return "admin-student-page";
    }

}
