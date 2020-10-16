package com.vozniuk.deanery.json.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Collection;

public class CollectionToJSONConverter {

    private CollectionToJSONConverter() {
    }

    public static <T> String writeAsJSON(Class<? extends T> type, JsonSerializer<T> serializer, Collection<T> objects) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(type, serializer);
        mapper.registerModule(simpleModule);
        return mapper.writeValueAsString(objects);
    }

}
