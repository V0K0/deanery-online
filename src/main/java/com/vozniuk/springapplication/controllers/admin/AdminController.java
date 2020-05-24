package com.vozniuk.springapplication.controllers.admin;

import com.vozniuk.springapplication.domain.data.university.Teacher;
import com.vozniuk.springapplication.service.impl.PlanServiceImpl;
import com.vozniuk.springapplication.service.impl.StudentServiceImpl;
import com.vozniuk.springapplication.service.impl.SubjectServiceImpl;
import com.vozniuk.springapplication.service.impl.TeacherServiceImpl;
import com.vozniuk.springapplication.service.services.TeacherService;
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

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @GetMapping("/admin-page")
    public String admin() {
        return "admin-page";
    }

    @GetMapping("/admin-page/study")
    public String studyPage(Model model) {
        Long countOfSubject = subjectServiceImpl.getSubjectsCount();
        pushInModel(model, "countOfSubjects", countOfSubject);
        return "admin-study-page";
    }

    @GetMapping("admin-page/students")
    public String studentsPage(Model model){
        Long countOfStudents = studentServiceImpl.getStudentsCount();
        pushInModel(model, "countOfStudents", countOfStudents);
        return "admin-student-page";
    }

    @GetMapping("admin-page/teachers")
    public String teachersPage(Model model){
        Long countOfTeachers = teacherServiceImpl.getTeachersCount();
        pushInModel(model, "countOfTeachers", countOfTeachers);
        return "admin-teacher-page";
    }

    private void pushInModel(Model model, String paramName, Object paramValue) {
        model.addAttribute(paramName, paramValue);
    }

}
