package com.matchbook.sdk.configs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.matchbook.sdk.ClientConfig;
import com.squareup.okhttp.OkHttpClient;

public final class ClientConnectionManager {

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
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    private OkHttpClient buildOkHttpClient() {
        return new OkHttpClient();
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
