package com.vozniuk.deanery.repository;

import com.vozniuk.deanery.data.university.Schedule;
import com.vozniuk.deanery.data.university.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select sch from Schedule sch " +
            "left join fetch sch.subject sub " +
            "left join fetch sub.teachers " +
            "where sch.group =:group and sch.dayOfWeek=:dayOfWeek")
    Optional<List<Schedule>> findByGroupAndDayOfWeekOrderByLessonTime(UniversityGroup group, String dayOfWeek);
}
