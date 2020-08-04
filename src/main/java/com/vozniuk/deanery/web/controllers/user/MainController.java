package com.vozniuk.deanery.web.controllers.user;

import com.vozniuk.deanery.domain.data.university.Student;
import com.vozniuk.deanery.domain.data.university.StudyingPlan;
import com.vozniuk.deanery.domain.data.university.Subject;
import com.vozniuk.deanery.domain.data.user.Role;
import com.vozniuk.deanery.domain.data.user.User;
import com.vozniuk.deanery.service.impl.StudentServiceImpl;
import com.vozniuk.deanery.service.impl.SubjectServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private SubjectServiceImpl subjectServiceImpl;

    private StudentServiceImpl studentServiceImpl;

    private final Logger logger = LogManager.getLogger(MainController.class);

    @Autowired
    public void setSubjectServiceImpl(SubjectServiceImpl subjectServiceImpl) {
        this.subjectServiceImpl = subjectServiceImpl;
    }
    @Autowired
    public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @GetMapping("/")
    public String startPage(){
        return "startPage";
    }

    @GetMapping("/home")
    public String homePage(@AuthenticationPrincipal User user, Model model){
        if (user.getAuthorities().contains(Role.ADMIN)){
            logger.info("Admin: {} logged in", user.getUsername());
            return "forward:admin-page";
        }
        loadStudentInModel(user, model);

        logger.info("User: {} logged in", user.getUsername());

        return "home";
    }

    @GetMapping("/plan")
    public String planPage(@AuthenticationPrincipal User user, Model model){
        loadStudentInModel(user, model);
        loadPlanInModel(model);
        logger.info("GET plan for: {}", user.getUsername());
        return "studying-plan";
    }

    private void loadStudentInModel(User user, Model model){
        Student currentStudent = studentServiceImpl.getStudentById(user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("student", currentStudent);
    }

    private void loadPlanInModel(Model model){
       Student currentStudent = (Student) model.getAttribute("student");
       if (currentStudent != null && currentStudent.getStudentGroup() != null){
           StudyingPlan plan = currentStudent.getStudentGroup().getGroupPlan();
           List<Subject> subjects = subjectServiceImpl.getAllByPlan(plan);
           List<Subject>  courseWorkSubjects = subjects.stream().filter(Subject::isCourseWork).collect(Collectors.toList());
           model.addAttribute("listOfSubjects", subjects);
           model.addAttribute("courseWorkSubjects", courseWorkSubjects);
       } else {
           model.addAttribute("listOfSubjects", new ArrayList<Subject>());
       }
    }

}
