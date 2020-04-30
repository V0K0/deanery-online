package com.vozniuk.springapplication.repositories;

import com.vozniuk.springapplication.domain.data.university.Schedule;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByGroupAndDayOfWeekOrderByLessonTime(UniversityGroup group, String dayOfWeek);
}
