package com.vozniuk.deanery.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vozniuk.deanery.domain.data.university.Student;

import java.io.IOException;

public class StudentSerializer extends StdSerializer<Student> {

    public StudentSerializer() {
        this(null);
    }

    public StudentSerializer(Class<Student> t) {
        super(t);
    }

    @Override
    public void serialize(Student student, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", student.getId());
        jsonGenerator.writeStringField("name", student.getName());
        jsonGenerator.writeStringField("lastname", student.getLastname());
        jsonGenerator.writeObjectField("stGroup", student.getStudentGroup().getGroupCode());
        jsonGenerator.writeEndObject();
    }
}



