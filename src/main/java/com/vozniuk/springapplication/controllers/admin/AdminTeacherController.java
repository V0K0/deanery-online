package com.vozniuk.springapplication.controllers.admin;

import com.vozniuk.springapplication.domain.data.university.Teacher;
import com.vozniuk.springapplication.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminTeacherController {

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @PutMapping("/admin-page/teachers/update")
    @ResponseBody
    public String updateTeacher(@RequestParam Map<String, String> allParams) {
        Teacher teacher = teacherServiceImpl.getTeacherById(Integer.parseInt(allParams.get("id")));
        if (teacher != null) {
            fetchAndSetTeacherAttributes(teacher, allParams);
            if (!teacher.getName().isBlank() && !teacher.getLastname().isBlank()) {
                teacherServiceImpl.addOrUpdateTeacher(teacher);
                return "Success";
            }
            return "Denied! Tried to push nulls.";
        }
        return "Failed";
    }

    @PostMapping("/admin-page/teachers/create")
    public String createTeacher(@RequestParam Map<String, String> allParams) {
        Teacher teacher = new Teacher();
        fetchAndSetTeacherAttributes(teacher, allParams);
        if (!teacher.getName().isBlank() && !teacher.getLastname().isBlank()) {
            if (teacherServiceImpl.getByNameAndLastname(teacher.getName(), teacher.getLastname()) == null) {
                teacherServiceImpl.addOrUpdateTeacher(teacher);
            }
        }

        return "redirect:/admin-page/teachers";
    }

    private void fetchAndSetTeacherAttributes(Teacher teacher, Map<String, String> allParams) {
        String name = allParams.get("name");
        String lastname = allParams.get("lastname");
        String patronymic = allParams.get("patron");
        String phone = allParams.get("phone").replace("-", "");
        setAttributes(name, lastname, patronymic, phone, teacher);
    }

    private void setAttributes(String name, String lastname, String patronymic, String phone, Teacher teacher) {
        if (name != null && !name.isBlank()) {
            teacher.setName(name);
        }
        if (lastname != null && !lastname.isBlank()) {
            teacher.setLastname(lastname);
        }
        if (patronymic != null && !patronymic.isBlank()) {
            teacher.setPatronymic(patronymic);
        }
        if (phone != null && !phone.isBlank()) {
            teacher.setPhone(phone);
        }
    }

}
