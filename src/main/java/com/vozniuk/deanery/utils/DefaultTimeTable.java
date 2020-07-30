package com.vozniuk.deanery.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultTimeTable implements TimeTable {
    public Map<String, Integer> getTimeMap() {

        Map<String, Integer> lessonsMap = new HashMap<>(5);
        lessonsMap.put("08:00:00", 0);
        lessonsMap.put( "09:35:00", 1);
        lessonsMap.put("11:20:00", 2);
        lessonsMap.put("12:55:00", 3);
        lessonsMap.put( "14:30:00", 4);

        return lessonsMap;
    }
}
