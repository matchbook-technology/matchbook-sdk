package com.matchbook.sdk.rest;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

import com.matchbook.sdk.rest.configs.HttpClient;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.configs.wrappers.HttpClientWrapper;
import com.matchbook.sdk.rest.configs.wrappers.SerializerWrapper;
import com.matchbook.sdk.rest.workers.SessionManager;

public class ConnectionManager implements Closeable {

    private final ClientConfig clientConfig;
    private final HttpClient httpClient;
    private final Serializer serializer;

    private SessionManager sessionManager;

    public ConnectionManager(ConnectionManager.Builder builder) {
        this.clientConfig = builder.clientConfig;
        this.httpClient = builder.httpClient;
        this.serializer = builder.serializer;

        if (builder.sessionAutoManage) {
            sessionManager = new SessionManager(this);
            sessionManager.keepAlive();
        }
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

    @Override
    public void close() throws IOException {
        httpClient.close();
        if (Objects.nonNull(sessionManager)) {
            sessionManager.stop();
        }
    }

    public static class Builder {

        private final ClientConfig clientConfig;
        private final HttpClient httpClient;
        private final Serializer serializer;

        private boolean sessionAutoManage;

        public Builder(ClientConfig clientConfig) {
            this.clientConfig = clientConfig;
            this.httpClient = new HttpClientWrapper(clientConfig.getHttpConfig());
            this.serializer = new SerializerWrapper();
            sessionAutoManage = true;
        }

        public Builder sessionAutoManage(boolean sessionAutoManage) {
            this.sessionAutoManage = sessionAutoManage;
            return this;
        }

        public ConnectionManager build() {
            return new ConnectionManager(this);
        }
    }
}
