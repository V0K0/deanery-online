package com.vozniuk.deanery.domain.data.university;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Column(nullable = false, length = 2)
    private Integer term;

    @Column(name = "day_of_week", length = 20)
    private String dayOfWeek;

    @Column(name = "week_type", length = 20)
    private String weekType;

    @Column(name = "lesson_time")
    private Time lessonTime;

    @Column(name = "lesson_place")
    private String lessonPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private UniversityGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

}
