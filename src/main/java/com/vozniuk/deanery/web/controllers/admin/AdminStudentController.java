package com.vozniuk.deanery.web.controllers.admin;

import com.vozniuk.deanery.domain.data.university.Student;
import com.vozniuk.deanery.domain.data.university.UniversityGroup;
import com.vozniuk.deanery.service.impl.GroupServiceImpl;
import com.vozniuk.deanery.service.impl.StudentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminStudentController {

    private StudentServiceImpl studentServiceImpl;

    private GroupServiceImpl groupServiceImpl;

    private final Logger logger = LogManager.getLogger(AdminStudentController.class);

    @Autowired
    public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @Autowired
    public void setGroupServiceImpl(GroupServiceImpl groupServiceImpl) {
        this.groupServiceImpl = groupServiceImpl;
    }

    @PutMapping("/admin-page/students/update")
    @ResponseBody
    public Object updateStudent(@RequestParam Map<String, String> allParams) {
        int id = Integer.parseInt(allParams.get("id"));
        Student oldStudent = studentServiceImpl.getStudentById(id);
        if (oldStudent != null) {
            fetchAndSetStudentUpdateAttributes(oldStudent, allParams);
            studentServiceImpl.addOrUpdateStudent(oldStudent);
            logger.info("Successfully updated student with id {} by admin", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Updated");
        }

        logger.info("Failed to update student with id {} by admin", id);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Denied");
    }

    private void fetchAndSetStudentUpdateAttributes(Student student, Map<String, String> attributes) {
        String newStudentName = attributes.get("name");
        String newStudentLastname = attributes.get("lastname");
        UniversityGroup newGroup = groupServiceImpl.getByGroupCode(attributes.get("stGroup"));
        setAttributes(newStudentName, newStudentLastname, newGroup, student);
    }

    private void setAttributes(String newStudentName, String newStudentLastname, UniversityGroup newGroup, Student student) {
        if (newStudentName != null && !newStudentName.isBlank()) {
            student.setName(newStudentName);
        }
        if (newStudentLastname != null && !newStudentLastname.isBlank()) {
            student.setLastname(newStudentLastname);
        }
        if (newGroup != null) {
            student.setStudentGroup(newGroup);
        }
    }


}
