package com.vozniuk.springapplication.service.services;

import com.vozniuk.springapplication.domain.data.university.Schedule;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;

import java.util.List;

public interface ScheduleService {

    Schedule addSchedule(Schedule schedule);

    void deleteSchedule(Schedule schedule);

    Schedule getScheduleById(Integer id);

    Schedule editSchedule(Schedule schedule);

    List<Schedule> getAllForGroupOnDay(UniversityGroup group, String day);


}
