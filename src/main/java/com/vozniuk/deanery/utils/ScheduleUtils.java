package com.vozniuk.deanery.utils;

import com.vozniuk.deanery.data.university.Schedule;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ScheduleUtils {

    private final TimeTable timeTable;
    private final Map<String, Integer> lessonsTimeMap;

    public ScheduleUtils(TimeTable timeTable) {
        this.timeTable = timeTable;
        lessonsTimeMap = timeTable.getTimeMap();
    }

    public ScheduleUtils() {
        timeTable = new DefaultTimeTable();
        lessonsTimeMap = timeTable.getTimeMap();
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public int findLessonIndexInSchedule(Schedule schedule) {
        String lessonTime = schedule.getLessonTime().toString();
        return lessonsTimeMap.get(lessonTime);
    }


}
