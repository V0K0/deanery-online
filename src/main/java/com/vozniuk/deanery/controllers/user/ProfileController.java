package com.vozniuk.deanery.controllers.user;

import com.vozniuk.deanery.domain.data.university.Student;
import com.vozniuk.deanery.domain.data.user.User;
import com.vozniuk.deanery.service.impl.GroupServiceImpl;
import com.vozniuk.deanery.service.impl.StudentServiceImpl;
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

    private StudentServiceImpl studentServiceImpl;

    private GroupServiceImpl groupServiceImpl;

    @Autowired
    public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }
    @Autowired
    public void setGroupServiceImpl(GroupServiceImpl groupServiceImpl) {
        this.groupServiceImpl = groupServiceImpl;
    }

    @GetMapping("/profile")
    public String profileInfo(@AuthenticationPrincipal User user, Model model) {
        pushStudentInModel(user, model);

        return "profile";
    }

    @GetMapping("/profile/edit")
    public String getEditProfile(@AuthenticationPrincipal User user, Model model) {
        pushStudentInModel(user, model);

        return "edit";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@AuthenticationPrincipal User user, @RequestParam Map<String, String> allParams, Model model) {
        pushStudentInModel(user, model);
        Student current = (Student) model.getAttribute("student");
        if (current != null) {
            updateStudent(current, allParams);
        }
       return "redirect:/profile";
    }


    private void pushStudentInModel(User user, Model model) {
        Student currentStudent = studentServiceImpl.getStudentById(user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("student", currentStudent);
    }

    private void updateStudent(Student student, Map<String, String> params) {
        student.setName(params.get("name"));
        student.setLastname(params.get("lastname"));
        student.setPatronymic(params.get("patronymic"));

        if (!params.get("dateOfBirth").isEmpty()) {
            Date date = Date.valueOf(params.get("dateOfBirth"));
            if (date.compareTo(Date.valueOf(LocalDate.now())) <= 0){
                student.setDateOfBirth(date);
            }
        }

        student.setAddress(params.get("address"));

        if (!params.get("phone").isEmpty()) {
            String number = params.get("phone").replace("-", "");
            student.setPhone(number);
        }

        student.setStudentGroup(groupServiceImpl.getByGroupCode(params.get("group")));
        studentServiceImpl.addOrUpdateStudent(student);
    }

}
