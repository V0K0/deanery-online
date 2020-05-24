package com.vozniuk.springapplication.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vozniuk.springapplication.domain.data.university.Subject;
import com.vozniuk.springapplication.domain.data.university.Teacher;

import java.io.IOException;

public class TeacherSerializer extends StdSerializer<Teacher> {

    public TeacherSerializer() {
        this(null);
    }

    public TeacherSerializer(Class<Teacher> t) {
        super(t);
    }

    @Override
    public void serialize(Teacher teacher, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", teacher.getTeacherId());
        jsonGenerator.writeStringField("name", teacher.getName());
        jsonGenerator.writeStringField("lastname", teacher.getLastname());
        if (teacher.getPhone() != null){
            jsonGenerator.writeStringField("phone", teacher.getPhone());
        } else {
            jsonGenerator.writeStringField("phone", "-");
        }
        jsonGenerator.writeArrayFieldStart("subjectName");
        if (teacher.getSubjects().isEmpty()){
            jsonGenerator.writeString("-");
        } else {
            for (Subject s : teacher.getSubjects()){
                jsonGenerator.writeString(s.getSubjectName());
            }
        }
        jsonGenerator.writeEndArray();


        jsonGenerator.writeEndObject();
    }


}
