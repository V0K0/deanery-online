package com.vozniuk.springapplication.service.impl;

import com.vozniuk.springapplication.domain.data.university.Schedule;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import com.vozniuk.springapplication.repositories.ScheduleRepository;
import com.vozniuk.springapplication.service.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllForGroupOnDay(UniversityGroup group, String day) {
        return scheduleRepository.findByGroupAndDayOfWeekOrderByLessonTime(group, day);
    }

    @Override
    public Schedule addOrUpdateSchedule(Schedule schedule) {
        scheduleRepository.saveAndFlush(schedule);
        return schedule;
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    @Override
    public Schedule getScheduleById(Integer id) {
        return scheduleRepository.findById(id).isPresent() ? scheduleRepository.findById(id).get() : null;
    }


}
