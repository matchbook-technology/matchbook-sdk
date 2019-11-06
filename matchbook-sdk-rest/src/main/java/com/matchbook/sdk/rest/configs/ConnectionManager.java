package com.matchbook.sdk.rest.configs;

import java.io.Closeable;
import java.util.Objects;

import com.matchbook.sdk.rest.configs.wrappers.HttpClientWrapper;
import com.matchbook.sdk.rest.configs.wrappers.SerializerWrapper;
import com.matchbook.sdk.rest.workers.SessionKeepAliveScheduler;

public class ConnectionManager implements Closeable {

    private final ClientConfig clientConfig;
    private final HttpClient httpClient;
    private final Serializer serializer;
    private final boolean autoManageSession;

    private SessionKeepAliveScheduler sessionKeepAliveScheduler;

    private ConnectionManager(ConnectionManager.Builder builder) {
        this.clientConfig = builder.clientConfig;
        this.httpClient = builder.httpClient;
        this.serializer = builder.serializer;
        this.autoManageSession = builder.autoManageSession;

        if (autoManageSession) {
            sessionKeepAliveScheduler = new SessionKeepAliveScheduler(this);
            sessionKeepAliveScheduler.start();
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

    public boolean isSessionAutoManaged() {
        return autoManageSession;
    }

    @Override
    public void close() {
        httpClient.close();
        if (Objects.nonNull(sessionKeepAliveScheduler)) {
            sessionKeepAliveScheduler.stop();
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
