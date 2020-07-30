package com.vozniuk.deanery.web.controllers.user;


import com.vozniuk.deanery.domain.data.university.Schedule;
import com.vozniuk.deanery.domain.data.university.Student;
import com.vozniuk.deanery.domain.data.user.User;
import com.vozniuk.deanery.service.impl.ScheduleServiceImpl;
import com.vozniuk.deanery.service.impl.StudentServiceImpl;
import com.vozniuk.deanery.utils.Days;
import com.vozniuk.deanery.utils.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ScheduleController {

    private StudentServiceImpl studentServiceImpl;

    private ScheduleServiceImpl scheduleServiceImpl;

    private final ScheduleUtils scheduleUtils;

    @Autowired
    public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @Autowired
    public void setScheduleServiceImpl(ScheduleServiceImpl scheduleServiceImpl) {
        this.scheduleServiceImpl = scheduleServiceImpl;
    }

    public ScheduleController(ScheduleUtils scheduleUtils) {
        this.scheduleUtils = scheduleUtils;
    }

    @GetMapping("/schedule")
    public String getSchedule(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());
        pushUserInModel(user, model);
        pushScheduleForStudent(model);
        return "schedule";
    }

    private void pushUserInModel(User user, Model model) {
        Student currentStudent = studentServiceImpl.getStudentById(user.getId());
        model.addAttribute("student", currentStudent);
    }

    private void pushScheduleForStudent(Model model) {
        Student current = (Student) model.getAttribute("student");

        if (current != null) {
            loadScheduleForDayInModel(current, model, Days.MONDAY);
            loadScheduleForDayInModel(current, model, Days.TUESDAY);
            loadScheduleForDayInModel(current, model, Days.WEDNESDAY);
            loadScheduleForDayInModel(current, model, Days.THURSDAY);
            loadScheduleForDayInModel(current, model, Days.FRIDAY);
            model.addAttribute("scheduleUtils", scheduleUtils);
        }

    }

    private void loadScheduleForDayInModel(Student student, Model model, Days days) {
        List<Schedule> schedule = scheduleServiceImpl.getAllForGroupOnDay(student.getStudentGroup(), days.getDayName());
        model.addAttribute(days.getDayNum(), schedule.stream().filter(sch ->
                sch.getWeekType().equals(Days.WEEK_NUMERATOR.getDayName()) || sch.getWeekType().equals(Days.EVERY_WEEK.getDayName()))
                .collect(Collectors.toList()));
        model.addAttribute(days.getDayDenum(), schedule.stream().filter(sch -> sch.getWeekType().equals(Days.WEEK_DENOMINATOR.getDayName()))
                .collect(Collectors.toList()));
        model.addAttribute(days.name(), days.getDayName());
    }

}



