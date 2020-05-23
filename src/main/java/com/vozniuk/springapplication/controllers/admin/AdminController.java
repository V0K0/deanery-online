package com.vozniuk.springapplication.controllers.admin;

import com.vozniuk.springapplication.service.impl.PlanServiceImpl;
import com.vozniuk.springapplication.service.impl.StudentServiceImpl;
import com.vozniuk.springapplication.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private SubjectServiceImpl subjectServiceImpl;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping("/admin-page")
    public String admin() {
        return "admin-page";
    }


    @GetMapping("/admin-page/study")
    public String studyPage(Model model) {
        Long countOfSubject = subjectServiceImpl.getSubjectsCount();
        pushInModel(model, "countOfSubject", countOfSubject);
        return "admin-study-page";
    }

    @GetMapping("admin-page/students")
    public String studentsPage(Model model){
        Long countOfStudents = studentServiceImpl.getStudentsCount();
        pushInModel(model, "countOfStudents", countOfStudents);
        return "admin-student-page";
    }



    private void pushInModel(Model model, String paramName, Object paramValue) {
        model.addAttribute(paramName, paramValue);
    }

}
