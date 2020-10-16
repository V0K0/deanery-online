package com.vozniuk.deanery.json.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vozniuk.deanery.data.university.Teacher;
import com.vozniuk.deanery.json.serializer.TeacherSerializer;

public class TeacherToJSONConverter {

    private TeacherToJSONConverter() {
    }

    public static String convertTeacherToJSON(Teacher teacher) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Teacher.class, new TeacherSerializer());
        mapper.registerModule(simpleModule);
        return mapper.writeValueAsString(teacher);
    }
}
