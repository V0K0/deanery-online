package com.vozniuk.deanery.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vozniuk.deanery.data.university.Subject;

import java.io.IOException;

public class SubjectSerializer extends StdSerializer<Subject>{

    public SubjectSerializer() {
        this(null);
    }

    public SubjectSerializer(Class<Subject> t) {
        super(t);
    }

    @Override
    public void serialize(Subject subject, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", subject.getSubjectId());
        jsonGenerator.writeStringField("subjectName", subject.getSubjectName());
        jsonGenerator.writeNumberField("practicalHours", subject.getPracticalHours());
        jsonGenerator.writeNumberField("lectureHours", subject.getLectureHours());
        jsonGenerator.writeStringField("defenceType", subject.getDefenceType());
        jsonGenerator.writeBooleanField("courseWork", subject.isCourseWork());
        if (subject.getDefenceDate() != null){
            jsonGenerator.writeStringField("defenceDate", subject.getDefenceDate().toString());
        } else {
            jsonGenerator.writeStringField("defenceDate", "");
        }
        jsonGenerator.writeNumberField("plan", subject.getPlan().getId());
        jsonGenerator.writeEndObject();
    }
}
