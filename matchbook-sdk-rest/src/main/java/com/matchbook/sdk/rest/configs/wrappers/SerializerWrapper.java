package com.matchbook.sdk.rest.configs.wrappers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.configs.Serializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SerializerWrapper implements Serializer {

    private final ObjectMapper objectMapper;
    private final JsonFactory jsonFactory;
    private final Map<Class<?>, ObjectWriter> objectWriters;

    public SerializerWrapper() {
        objectMapper = initObjectMapper();
        jsonFactory = new JsonFactory();

        objectWriters = new HashMap<>();
    }

    private ObjectMapper initObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());

        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    @Override
    public <T> String writeObjectAsString(T object) throws IOException {
        ObjectWriter objectWriter = objectWriters.computeIfAbsent(object.getClass(), objectMapper::writerFor);
        return objectWriter.writeValueAsString(object);
    }

    @Override
    public Parser newParser(InputStream inputStream) {
        return new ParserWrapper(jsonFactory, inputStream);
    }

}
