package com.vozniuk.springapplication.controllers;

import com.vozniuk.springapplication.service.impl.PlanServiceImpl;
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
    private PlanServiceImpl planServiceImpl;

    @GetMapping("/admin-page")
    public String admin() {
        return "admin-page";
    }


    @GetMapping("/admin-page/study")
    public String studyPage(Model model) {
        pushInModel(model);
        return "admin-study-page";
    }


    private void pushInModel(Model model) {
        Long countOfSubject = subjectServiceImpl.getSubjectsCount();
        model.addAttribute("countSubjects", countOfSubject);
    }

}
