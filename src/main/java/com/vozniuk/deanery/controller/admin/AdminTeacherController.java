package com.vozniuk.deanery.controller.admin;

import com.vozniuk.deanery.data.university.Teacher;
import com.vozniuk.deanery.service.TeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin-page")
public class AdminTeacherController {

    private final TeacherService teacherService;

    private final Logger logger = LogManager.getLogger(AdminTeacherController.class);

    public AdminTeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public String teachersPage(Model model) {
        Long countOfTeachers = teacherService.getTeachersCount();
        model.addAttribute("countOfTeachers", countOfTeachers);
        return "admin-teacher-page";
    }

    @PostMapping("/teachers")
    public String createTeacher(@RequestParam Map<String, String> allParams) {
        try {
            Teacher teacher = Teacher.builder()
                    .lastname(allParams.get("lastname"))
                    .name(allParams.get("name"))
                    .patronymic(allParams.get("patron"))
                    .phone(allParams.get("phone"))
                    .build();

            teacherService.addOrUpdateTeacher(teacher);
            logger.info("Successfully added new teacher with id: {}", teacher.getTeacherId());
        } catch (Exception exception) {
            logger.error("Failed to create teacher cause: {}", exception.getMessage());
        }
        return "redirect:/admin-page/teachers";
    }

}
