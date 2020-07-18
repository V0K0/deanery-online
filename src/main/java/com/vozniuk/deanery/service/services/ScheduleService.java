package com.vozniuk.deanery.service.services;

import com.vozniuk.deanery.domain.data.university.Schedule;
import com.vozniuk.deanery.domain.data.university.UniversityGroup;

import java.util.List;

public interface ScheduleService {

    Schedule addOrUpdateSchedule(Schedule schedule);

    void deleteSchedule(Schedule schedule);

    Schedule getScheduleById(Integer id);

    List<Schedule> getAllForGroupOnDay(UniversityGroup group, String day);


}
