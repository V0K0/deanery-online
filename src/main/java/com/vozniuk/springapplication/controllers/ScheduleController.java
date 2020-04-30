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
        pushInModel(user, model);
        return "schedule";
    }

    private void pushInModel(User user, Model model) {
        Student currentStudent = studentServiceImpl.getStudentById(user.getId());
        model.addAttribute("student", currentStudent);
        pushScheduleForStudent(currentStudent, model);
    }

    private void pushScheduleForStudent(Student student, Model model){

        List<Schedule> schedulesMonday = scheduleServiceImpl.getAllForGroupOnDay(student.getGroup(), Days.MONDAY);
        List<Schedule> schedulesTuesday = scheduleServiceImpl.getAllForGroupOnDay(student.getGroup(), Days.TUESDAY);
        List<Schedule> schedulesWednesday = scheduleServiceImpl.getAllForGroupOnDay(student.getGroup(), Days.WEDNESDAY);
        List<Schedule> schedulesThursday = scheduleServiceImpl.getAllForGroupOnDay(student.getGroup(), Days.THURSDAY);
        List<Schedule> schedulesFriday = scheduleServiceImpl.getAllForGroupOnDay(student.getGroup(), Days.FRIDAY);

        model.addAttribute("scheduleMondayNum", schedulesMonday.stream().filter(schedule ->
                schedule.getWeekType().equals(Days.WEEK_NUMERATOR) || schedule.getWeekType().equals(Days.EVERY_WEEK))
                .collect(Collectors.toList()));
        model.addAttribute("scheduleMondayDenum", schedulesMonday.stream().filter(schedule -> schedule.getWeekType().equals(Days.WEEK_DENOMINATOR))
                .collect(Collectors.toList()));
        model.addAttribute("MONDAY", Days.MONDAY);

        model.addAttribute("scheduleTuesdayNum", schedulesTuesday.stream().filter(schedule ->
                schedule.getWeekType().equals(Days.WEEK_NUMERATOR) || schedule.getWeekType().equals(Days.EVERY_WEEK))
                .collect(Collectors.toList()));
        model.addAttribute("scheduleTuesdayDenum", schedulesTuesday.stream().filter(schedule -> schedule.getWeekType().equals(Days.WEEK_DENOMINATOR))
                .collect(Collectors.toList()));
        model.addAttribute("TUESDAY", Days.TUESDAY);

        model.addAttribute("scheduleWednesdayNum", schedulesWednesday.stream().filter(schedule ->
                schedule.getWeekType().equals(Days.WEEK_NUMERATOR) || schedule.getWeekType().equals(Days.EVERY_WEEK))
                .collect(Collectors.toList()));
        model.addAttribute("scheduleWednesdayDenum", schedulesWednesday.stream().filter(schedule -> schedule.getWeekType().equals(Days.WEEK_DENOMINATOR))
                .collect(Collectors.toList()));
        model.addAttribute("WEDNESDAY", Days.WEDNESDAY);

        model.addAttribute("scheduleThursdayNum", schedulesThursday.stream().filter(schedule ->
                schedule.getWeekType().equals(Days.WEEK_NUMERATOR) || schedule.getWeekType().equals(Days.EVERY_WEEK))
                .collect(Collectors.toList()));
        model.addAttribute("scheduleThursdayDenum", schedulesThursday.stream().filter(schedule -> schedule.getWeekType().equals(Days.WEEK_DENOMINATOR))
                .collect(Collectors.toList()));
        model.addAttribute("THURSDAY", Days.THURSDAY);

        model.addAttribute("scheduleFridayNum", schedulesFriday.stream().filter(schedule ->
                schedule.getWeekType().equals(Days.WEEK_NUMERATOR) || schedule.getWeekType().equals(Days.EVERY_WEEK))
                .collect(Collectors.toList()));
        model.addAttribute("scheduleFridayDenum", schedulesFriday.stream().filter(schedule -> schedule.getWeekType().equals(Days.WEEK_DENOMINATOR))
                .collect(Collectors.toList()));
        model.addAttribute("FRIDAY", Days.FRIDAY);

        model.addAttribute("scheduleUtils", new ScheduleUtils(new DefaultTimeTable()));
    }

}
