package com.vozniuk.deanery.utils;

public enum Days {

    MONDAY("Понеділок", "scheduleMondayNum", "scheduleMondayDenum"),
    TUESDAY("Вівторок", "scheduleTuesdayNum", "scheduleTuesdayDenum"),
    WEDNESDAY ("Середа", "scheduleWednesdayNum", "scheduleWednesdayDenum"),
    THURSDAY ("Четверг", "scheduleThursdayNum","scheduleThursdayDenum"),
    FRIDAY ("П'ятниця", "scheduleFridayNum","scheduleFridayDenum"),
    WEEK_NUMERATOR ("Чисельник", "",""),
    WEEK_DENOMINATOR  ("Знаменник", "",""),
    EVERY_WEEK  ("Щотижня", "","");

    private final String dayName;

    private final String dayNum;

    private final String dayDenum;

    Days(String dayName, String dayNum, String dayDenum) {
        this.dayName = dayName;
        this.dayNum = dayNum;
        this.dayDenum = dayDenum;
    }

    public String getDayName() {
        return dayName;
    }

    public String getDayNum() {
        return dayNum;
    }

    public String getDayDenum() {
        return dayDenum;
    }
}
