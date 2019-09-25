package com.matchbook.sdk.rest;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

import com.matchbook.sdk.rest.configs.HttpClient;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.configs.wrappers.HttpClientWrapper;
import com.matchbook.sdk.rest.configs.wrappers.SerializerWrapper;
import com.matchbook.sdk.rest.workers.SessionManagerScheduler;

public class ConnectionManager implements Closeable {

    private final ClientConfig clientConfig;
    private final HttpClient httpClient;
    private final Serializer serializer;

    private SessionManagerScheduler sessionManagerScheduler;

    private ConnectionManager(ConnectionManager.Builder builder) {
        this.clientConfig = builder.clientConfig;
        this.httpClient = builder.httpClient;
        this.serializer = builder.serializer;

        if (builder.autoManageSession) {
            sessionManagerScheduler = new SessionManagerScheduler(this);
            sessionManagerScheduler.start();
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
    public void close() {
        httpClient.close();
        if (Objects.nonNull(sessionManagerScheduler)) {
            sessionManagerScheduler.stop();
        }
    }

    public static class Builder {

        private final ClientConfig clientConfig;
        private final HttpClient httpClient;
        private final Serializer serializer;

        private boolean autoManageSession;

        public Builder(ClientConfig clientConfig) {
            this.clientConfig = clientConfig;
            this.httpClient = new HttpClientWrapper(clientConfig.getHttpConfig());
            this.serializer = new SerializerWrapper();
            autoManageSession = true;
        }

        public Builder autoManageSession(boolean autoManageSession) {
            this.autoManageSession = autoManageSession;
            return this;
        }

        public ConnectionManager build() {
            return new ConnectionManager(this);
        }
    }
}
