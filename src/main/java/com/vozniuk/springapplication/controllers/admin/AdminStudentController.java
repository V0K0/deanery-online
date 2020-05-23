package com.vozniuk.springapplication.controllers.admin;

import com.vozniuk.springapplication.domain.data.university.Student;
import com.vozniuk.springapplication.domain.data.university.Subject;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import com.vozniuk.springapplication.service.impl.GroupServiceImpl;
import com.vozniuk.springapplication.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminStudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private GroupServiceImpl groupServiceImpl;

    @PutMapping("/admin-page/study/student/update")
    @ResponseBody
    public String updateSubject(@RequestParam Map<String, String> allParams) {
        int id = Integer.parseInt(allParams.get("id"));
        Student oldStudent = studentServiceImpl.getStudentById(id);
        if (oldStudent != null) {
            fetchAndSetStudentUpdateAttributes(oldStudent, allParams);
            studentServiceImpl.addOrUpdateStudent(oldStudent);
            return "success";
        }
        return "error";
    }

    private void fetchAndSetStudentUpdateAttributes(Student student, Map<String, String> attributes) {
        String newStudentName = attributes.get("name");
        String newStudentLastname = attributes.get("lastname");
        UniversityGroup newGroup = groupServiceImpl.getByGroupCode(attributes.get("stGroup"));
        setAttributes(newStudentName, newStudentLastname, newGroup, student);
    }

    private void setAttributes(String newStudentName, String newStudentLastname, UniversityGroup newGroup, Student student) {
        if (!newStudentName.isBlank()) {
            student.setName(newStudentName);
        }
        if (!newStudentLastname.isBlank()) {
            student.setLastname(newStudentLastname);
        }
        if (newGroup != null) {
            student.setGroup(newGroup);
        }
    }


}
