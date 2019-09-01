package com.matchbook.sdk.rest;

import com.matchbook.sdk.rest.configs.HttpClient;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.configs.wrappers.HttpClientWrapper;
import com.matchbook.sdk.rest.configs.wrappers.SerializerWrapper;
import com.matchbook.sdk.rest.workers.SessionManager;

public class ConnectionManager {

    private ClientConfig clientConfig;
    private HttpClient httpClient;
    private Serializer serializer;

    public ConnectionManager(ConnectionManager.Builder builder) {
        this.clientConfig = builder.clientConfig;
        this.httpClient = builder.httpClient;
        this.serializer = builder.serializer;

        if (builder.sessionAutoManage) {
            SessionManager sessionManager = new SessionManager(this);
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

    public static class Builder {

        private final ClientConfig clientConfig;
        private final HttpClient httpClient;
        private final Serializer serializer;

        private boolean sessionAutoManage = true;

        public Builder sessionAutoManage(boolean sessionAutoManage) {
            this.sessionAutoManage = sessionAutoManage;
            return this;
        }

        public Builder(ClientConfig clientConfig) {
            this.clientConfig = clientConfig;

            this.httpClient = new HttpClientWrapper(clientConfig.getHttpConfig());
            this.serializer = new SerializerWrapper();
        }

        public ConnectionManager build() {
            return new ConnectionManager(this);
        }
    }
}
