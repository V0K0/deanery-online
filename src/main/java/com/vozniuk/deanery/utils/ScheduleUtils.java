package com.vozniuk.deanery.utils;

import com.vozniuk.deanery.domain.data.university.Schedule;

import java.util.HashMap;

public class ScheduleUtils {

    private TimeTable timeTable;
    private HashMap<String, Integer> lessonsTimeMap;
    private Schedule schedule;

    public ScheduleUtils(TimeTable timeTable) {
        this.timeTable = timeTable;
        lessonsTimeMap = timeTable.getTimeMap();
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
        lessonsTimeMap = timeTable.getTimeMap();
    }

    public int findLessonIndexInSchedule(Schedule schedule) {
        String lessonTime = schedule.getLessonTime().toString();
        return lessonsTimeMap.get(lessonTime);
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

}