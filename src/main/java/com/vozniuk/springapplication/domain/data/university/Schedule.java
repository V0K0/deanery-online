package com.vozniuk.springapplication.domain.data.university;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Getter
@Setter
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "schedule_id")
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @NotNull
    private UniversityGroup group;

    @ManyToOne(fetch = FetchType.LAZY,   cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinColumn(name = "subject_id")
    @NotNull
    private Subject subject;

    private Integer term;

    @JoinColumn(name = "day_of_week")
    private String dayOfWeek;

    @JoinColumn(name = "week_type")
    private String weekType;

    @JoinColumn(name = "lesson_time")
    private Time lessonTime;

    @JoinColumn(name = "lesson_place")
    private String lessonPlace;

}
