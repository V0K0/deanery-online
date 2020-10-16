package com.vozniuk.deanery.controller.user;

import com.vozniuk.deanery.data.university.Student;
import com.vozniuk.deanery.data.university.UniversityGroup;
import com.vozniuk.deanery.data.user.User;
import com.vozniuk.deanery.service.GroupService;
import com.vozniuk.deanery.service.StudentService;
import com.vozniuk.deanery.ui.model.StudentModelLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class ProfileController {

    private final StudentService studentService;
    private final GroupService groupService;

    private final Logger logger = LogManager.getLogger(ProfileController.class);
    private final StudentModelLoader studentModelLoader;

    @Autowired
    public ProfileController(StudentModelLoader studentModelLoader, StudentService studentService, GroupService groupService) {
        this.studentModelLoader = studentModelLoader;
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/profile")
    public String profileInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());
        studentModelLoader.load(user, model);
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String getEditProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());
        studentModelLoader.load(user, model);
        return "edit";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@AuthenticationPrincipal User user, @RequestParam Map<String, String> studentProfileParams) {
        try {
            UniversityGroup universityGroup = groupService.getByGroupCode(studentProfileParams.get("group"));
            Student student = Student.builder()
                    .address(studentProfileParams.getOrDefault("address", null))
                    .name(studentProfileParams.get("name"))
                    .lastname(studentProfileParams.get("lastname"))
                    .patronymic(studentProfileParams.getOrDefault("patronymic", null))
                    .phone(studentProfileParams.getOrDefault("phone", null))
                    .studentGroup(universityGroup)
                    .build();
            student.setId(user.getId());

            String stringDate = studentProfileParams.getOrDefault("dateOfBirth", null);
            if (stringDate != null && Date.valueOf(stringDate).compareTo(Date.valueOf(LocalDate.now())) < 0) {
                student.setDateOfBirth(Date.valueOf(stringDate));
            }
            studentService.addOrUpdateStudent(student);
            universityGroup.addStudent(student);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return "edit";
        }
        logger.info("User: {} updated profile", user.getUsername());
        return "redirect:/profile";
    }


}
