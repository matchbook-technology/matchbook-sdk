package com.matchbook.sdk.core.configs;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.matchbook.sdk.core.ClientConfig;
import com.squareup.okhttp.OkHttpClient;

public final class ClientConnectionManager {

    private String TOKEN;
    private final ClientConfig clientConfig;
    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;

    public ClientConnectionManager(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        this.httpClient = buildOkHttpClient();
        this.mapper = buildObjectMapper();
    }

    private ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    private OkHttpClient buildOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
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

    public ObjectMapper getMapper() {
        return mapper;
    }
}
