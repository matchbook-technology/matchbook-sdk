package com.matchbook.sdk.rest.configs.wrappers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.configs.Serializer;

public class SerializerWrapper implements Serializer {

    private final ObjectMapper objectMapper;
    private final JsonFactory jsonFactory;
    private final Map<Class<?>, ObjectWriter> objectWriters;
    private final Map<Class<?>, ObjectReader> objectReaders;

    public SerializerWrapper() {
        objectMapper = initObjectMapper();
        jsonFactory = new JsonFactory();

        objectWriters = new HashMap<>();
        objectReaders = new HashMap<>();
    }

    private ObjectMapper initObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        final Module module = caseInsensitiveEnumModule();
        mapper.registerModule(module);
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());

        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    private Module caseInsensitiveEnumModule() {
        SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new BeanDeserializerModifier() {

            @SuppressWarnings("unchecked")
            @Override
            public JsonDeserializer<Enum> modifyEnumDeserializer(DeserializationConfig config, JavaType type,
                    BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
                return new JsonDeserializer<Enum>() {

                    @Override
                    public Enum<?> deserialize(JsonParser jsonParser,
                            DeserializationContext context) throws IOException {
                        Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
                        String enumValue = jsonParser.getValueAsString().replaceAll("-", "_").toUpperCase();
                        return Enum.valueOf(rawClass, enumValue);
                    }
                };
            }
        });
        return module;
    }

    @Override
    public <T> String writeObjectAsString(T object) throws IOException {
        ObjectWriter objectWriter = objectWriters.computeIfAbsent(object.getClass(), objectMapper::writerFor);
        return objectWriter.writeValueAsString(object);
    }

    @Override
    public Parser newParser(InputStream inputStream) throws IOException {
        return new ParserWrapper(jsonFactory, inputStream);
    }

    @Deprecated
    @Override
    public <T> T readObject(InputStream inputStream, Class<T> type) throws IOException {
        ObjectReader objectReader = objectReaders.computeIfAbsent(type, objectMapper::readerFor);
        return objectReader.readValue(inputStream);
    }

}
