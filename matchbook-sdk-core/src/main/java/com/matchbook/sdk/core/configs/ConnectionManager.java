package com.matchbook.sdk.core.configs;

import com.matchbook.sdk.core.configs.wrappers.HttpClientWrapper;
import com.matchbook.sdk.core.configs.wrappers.SerializerWrapper;

public final class ConnectionManager {

    private final ClientConfig clientConfig;
    private final HttpClient httpClient;
    private final Serializer serializer;

    public ConnectionManager(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;

        this.httpClient = new HttpClientWrapper();
        this.serializer = new SerializerWrapper();
    }

    public ClientConfig getClientConfig() {
        return clientConfig;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public Serializer getSerializer() {
        return serializer;
    }

}
