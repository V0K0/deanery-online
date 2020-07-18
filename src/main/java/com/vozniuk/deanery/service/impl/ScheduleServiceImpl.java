package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.domain.data.university.Schedule;
import com.vozniuk.deanery.domain.data.university.UniversityGroup;
import com.vozniuk.deanery.repositories.ScheduleRepository;
import com.vozniuk.deanery.service.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Autowired
    public void setScheduleRepository(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

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
