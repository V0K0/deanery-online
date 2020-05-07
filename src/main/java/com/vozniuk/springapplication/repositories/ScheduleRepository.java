package com.vozniuk.springapplication.repositories;

import com.vozniuk.springapplication.domain.data.university.Schedule;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByGroupAndDayOfWeekOrderByLessonTime(UniversityGroup group, String dayOfWeek);
}
