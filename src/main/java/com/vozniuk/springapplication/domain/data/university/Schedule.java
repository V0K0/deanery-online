package com.vozniuk.springapplication.domain.data.university;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

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

    @ManyToOne
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

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public UniversityGroup getGroup() {
        return group;
    }

    public void setGroup(UniversityGroup group) {
        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Time getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(Time lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getLessonPlace() {
        return lessonPlace;
    }

    public void setLessonPlace(String lessonPlace) {
        this.lessonPlace = lessonPlace;
    }

    public String getWeekType() {
        return weekType;
    }

    public void setWeekType(String weekType) {
        this.weekType = weekType;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}
