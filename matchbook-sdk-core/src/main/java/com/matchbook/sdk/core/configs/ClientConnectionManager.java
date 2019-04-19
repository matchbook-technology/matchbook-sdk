package com.matchbook.sdk.core.configs;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public final class ClientConnectionManager {

    private final ClientConfig clientConfig;
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ClientConnectionManager(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        this.httpClient = buildOkHttpClient();
        this.objectMapper = buildObjectMapper();
    }

    private ObjectMapper buildObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        final Module module = caseInsensitiveEnumModule();
        mapper.registerModule(module);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    private Module caseInsensitiveEnumModule() {
        SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new BeanDeserializerModifier() {

            @Override
            public JsonDeserializer<Enum> modifyEnumDeserializer(DeserializationConfig config, JavaType type,
                    BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
                return new JsonDeserializer<Enum>() {

                    @Override
                    public Enum deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
                        Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
                        return Enum.valueOf(rawClass, jsonParser.getValueAsString().toUpperCase());
                    }
                };
            }
        });
        return module;
    }

    private OkHttpClient buildOkHttpClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(5, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(5, TimeUnit.SECONDS);
        okHttpClient.setFollowRedirects(false);
        return okHttpClient;
    }

    public ClientConfig getClientConfig() {
        return clientConfig;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
