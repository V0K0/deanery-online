package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.data.university.Schedule;
import com.vozniuk.deanery.data.university.UniversityGroup;
import com.vozniuk.deanery.repository.ScheduleRepository;
import com.vozniuk.deanery.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule addOrUpdateSchedule(Schedule schedule) {
        return scheduleRepository.saveAndFlush(schedule);
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Schedule> getAllForGroupOnDay(UniversityGroup group, String day) {
        return scheduleRepository.findByGroupAndDayOfWeekOrderByLessonTime(group, day).orElse(new ArrayList<>());
    }
}
