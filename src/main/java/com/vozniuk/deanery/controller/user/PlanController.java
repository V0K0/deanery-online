package com.vozniuk.deanery.controller.user;

import com.vozniuk.deanery.data.university.Student;
import com.vozniuk.deanery.data.university.StudyingPlan;
import com.vozniuk.deanery.data.university.Subject;
import com.vozniuk.deanery.data.user.User;
import com.vozniuk.deanery.service.SubjectService;
import com.vozniuk.deanery.ui.model.StudentModelLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/plan")
public class PlanController {

    private final StudentModelLoader studentModelLoader;

    private final SubjectService subjectService;

    @Autowired
    public PlanController(StudentModelLoader studentModelLoader, SubjectService subjectService) {
        this.studentModelLoader = studentModelLoader;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String getPlanPage(@AuthenticationPrincipal User user, Model model) {

        String username = user.getUsername();
        studentModelLoader.load(user, model);
        model.addAttribute("username", username);

        Student currentStudent = (Student) model.getAttribute("student");
        if (currentStudent != null) {
            StudyingPlan plan = currentStudent.getStudentGroup().getGroupPlan();
            List<Subject> subjects = subjectService.getAllByPlan(plan);
            List<Subject> courseWorkSubjects = subjects.stream().filter(Subject::isCourseWork).collect(Collectors.toList());
            model.addAttribute("listOfSubjects", subjects);
            model.addAttribute("courseWorkSubjects", courseWorkSubjects);
        } else {
            model.addAttribute("listOfSubjects", new ArrayList<Subject>());
        }

        return "studying-plan";
    }


}
