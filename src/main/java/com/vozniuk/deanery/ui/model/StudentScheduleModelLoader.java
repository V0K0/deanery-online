package com.vozniuk.deanery.ui.model;

import com.vozniuk.deanery.data.university.Schedule;
import com.vozniuk.deanery.data.university.Student;
import com.vozniuk.deanery.service.ScheduleService;
import com.vozniuk.deanery.utils.Days;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Loads {@link Schedule} list for each day in {@link Model} for {@link Student}
 */
@Component
public class StudentScheduleModelLoader implements ModelLoader<Student> {

    private final ScheduleService scheduleService;

    public StudentScheduleModelLoader(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public void load(Student forStudent, Model model) {
        loadWeekSchedule(forStudent, model);
    }

    private void loadWeekSchedule(Student current, Model model) {
        loadScheduleForDayInModel(current, model, Days.MONDAY);
        loadScheduleForDayInModel(current, model, Days.TUESDAY);
        loadScheduleForDayInModel(current, model, Days.WEDNESDAY);
        loadScheduleForDayInModel(current, model, Days.THURSDAY);
        loadScheduleForDayInModel(current, model, Days.FRIDAY);
    }

    private void loadScheduleForDayInModel(Student student, Model model, Days days) {
        List<Schedule> schedule = scheduleService.getAllForGroupOnDay(student.getStudentGroup(), days.getDayName());

        model.addAttribute(days.getDayNum(), schedule
                .stream()
                .filter(sch -> sch.getWeekType()
                        .equals(Days.WEEK_NUMERATOR.getDayName()) || sch.getWeekType()
                        .equals(Days.EVERY_WEEK.getDayName()))
                .collect(Collectors.toList()));

        model.addAttribute(days.getDayDenum(), schedule
                .stream()
                .filter(sch -> sch.getWeekType()
                        .equals(Days.WEEK_DENOMINATOR.getDayName()))
                .collect(Collectors.toList()));

        model.addAttribute(days.name(), days.getDayName());
    }


}
