package com.vozniuk.deanery.controller.user;


import com.vozniuk.deanery.data.university.Student;
import com.vozniuk.deanery.data.user.User;
import com.vozniuk.deanery.ui.model.StudentModelLoader;
import com.vozniuk.deanery.ui.model.StudentScheduleModelLoader;
import com.vozniuk.deanery.utils.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {

    private final StudentModelLoader studentModelLoader;
    private final StudentScheduleModelLoader studentScheduleModelLoader;

    private ScheduleUtils scheduleUtils = new ScheduleUtils();

    @Autowired
    public ScheduleController(StudentModelLoader studentModelLoader, StudentScheduleModelLoader studentScheduleModelLoader) {
        this.studentModelLoader = studentModelLoader;
        this.studentScheduleModelLoader = studentScheduleModelLoader;
    }

    @GetMapping("/schedule")
    public String getSchedule(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());
        studentModelLoader.load(user, model);
        pushScheduleForStudent(model);
        return "schedule";
    }

    private void pushScheduleForStudent(Model model) {
        Student current = (Student) model.getAttribute("student");
        if (current != null) {
            studentScheduleModelLoader.load(current, model);
            model.addAttribute("scheduleUtils", scheduleUtils);
        }

    }

    @Autowired
    public void setScheduleUtils(ScheduleUtils scheduleUtils) {
        this.scheduleUtils = scheduleUtils;
    }
}



