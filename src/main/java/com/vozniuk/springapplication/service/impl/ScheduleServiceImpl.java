package com.vozniuk.springapplication.service.impl;

import com.vozniuk.springapplication.domain.data.university.Schedule;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import com.vozniuk.springapplication.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getAllForGroupOnDay(UniversityGroup group, String day) {
        return scheduleRepository.findByGroupAndDayOfWeekOrderByLessonTime(group, day);
    }
}
