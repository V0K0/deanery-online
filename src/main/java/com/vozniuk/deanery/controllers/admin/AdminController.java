package com.vozniuk.deanery.controllers.admin;

import com.vozniuk.deanery.service.impl.StudentServiceImpl;
import com.vozniuk.deanery.service.impl.SubjectServiceImpl;
import com.vozniuk.deanery.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private SubjectServiceImpl subjectServiceImpl;

    private StudentServiceImpl studentServiceImpl;

    private TeacherServiceImpl teacherServiceImpl;

    @Autowired
    public void setSubjectServiceImpl(SubjectServiceImpl subjectServiceImpl) {
        this.subjectServiceImpl = subjectServiceImpl;
    }

    @Autowired
    public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @Autowired
    public void setTeacherServiceImpl(TeacherServiceImpl teacherServiceImpl) {
        this.teacherServiceImpl = teacherServiceImpl;
    }

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
