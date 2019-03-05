package com.matchbook.sdk.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchbook.sdk.ClientConfig;
import com.squareup.okhttp.OkHttpClient;

public final class ClientConnectionManager {

    private final ClientConfig clientConfig;
    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;

    public ClientConnectionManager(ClientConfig clientConfig,
            OkHttpClient httpClient,
            ObjectMapper mapper) {
        this.clientConfig = clientConfig;
        this.httpClient = httpClient;
        this.mapper = mapper;
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
