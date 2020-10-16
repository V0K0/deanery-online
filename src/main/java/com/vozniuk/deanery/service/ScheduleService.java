package com.vozniuk.deanery.service;

import com.vozniuk.deanery.data.university.Schedule;
import com.vozniuk.deanery.data.university.UniversityGroup;

import java.util.List;

public interface ScheduleService {

    Schedule addOrUpdateSchedule(Schedule schedule);

    void deleteSchedule(Schedule schedule);

    Schedule getScheduleById(Long id);

    List<Schedule> getAllForGroupOnDay(UniversityGroup group, String day);


}
