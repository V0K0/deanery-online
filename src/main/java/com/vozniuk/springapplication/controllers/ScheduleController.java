package com.vozniuk.springapplication.controllers;


import com.vozniuk.springapplication.Utils.Days;
import com.vozniuk.springapplication.Utils.ScheduleUtils;
import com.vozniuk.springapplication.domain.data.university.DefaultTimeTable;
import com.vozniuk.springapplication.domain.data.university.Schedule;
import com.vozniuk.springapplication.domain.data.university.Student;
import com.vozniuk.springapplication.domain.data.user.User;
import com.vozniuk.springapplication.service.impl.ScheduleServiceImpl;
import com.vozniuk.springapplication.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ScheduleController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

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
            model.addAttribute("scheduleUtils", new ScheduleUtils(new DefaultTimeTable()));
        }

    }

    private void loadScheduleForDayInModel(Student student, Model model, Days days) {
        List<Schedule> schedule = scheduleServiceImpl.getAllForGroupOnDay(student.getGroup(), days.getDayName());
        model.addAttribute(days.getDayNum(), schedule.stream().filter(sch ->
                sch.getWeekType().equals(Days.WEEK_NUMERATOR.getDayName()) || sch.getWeekType().equals(Days.EVERY_WEEK.getDayName()))
                .collect(Collectors.toList()));
        model.addAttribute(days.getDayDenum(), schedule.stream().filter(sch -> sch.getWeekType().equals(Days.WEEK_DENOMINATOR.getDayName()))
                .collect(Collectors.toList()));
        model.addAttribute(days.name(), days.getDayName());
    }

}



