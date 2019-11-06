package com.matchbook.sdk.rest.configs;

import java.util.concurrent.TimeUnit;

public class HttpConfig {

    private final long connectionTimeoutMillis;
    private final long writeTimeoutMillis;
    private final long readTimeoutMillis;

    private HttpConfig(HttpConfig.Builder builder) {
        this.connectionTimeoutMillis = builder.connectionTimeoutMillis;
        this.readTimeoutMillis = builder.readTimeoutMillis;
        this.writeTimeoutMillis = builder.writeTimeoutMillis;
    }

    public long getConnectionTimeoutInMillis() {
        return connectionTimeoutMillis;
    }

    public long getWriteTimeoutInMillis() {
        return writeTimeoutMillis;
    }

    public long getReadTimeoutInMillis() {
        return readTimeoutMillis;
    }

    @Override
    public String toString() {
        return HttpConfig.class.getSimpleName() + " {" +
                "connectionTimeoutMillis=" + connectionTimeoutMillis +
                ", writeTimeoutMillis=" + writeTimeoutMillis +
                ", readTimeoutMillis=" + readTimeoutMillis +
                "}";
    }

    public static class Builder {

        private long connectionTimeoutMillis;
        private long writeTimeoutMillis;
        private long readTimeoutMillis;

        public Builder() {
            final long defaultTimeout = TimeUnit.SECONDS.toMillis(10);
            connectionTimeoutMillis = defaultTimeout;
            writeTimeoutMillis = defaultTimeout;
            readTimeoutMillis = defaultTimeout;
        }

        public Builder connectionTimeoutInMillis(long connectionTimeout) {
            return connectionTimeout(connectionTimeout, TimeUnit.MILLISECONDS);
        }

        public Builder connectionTimeout(long connectionTimeout, TimeUnit timeUnit) {
            this.connectionTimeoutMillis = timeUnit.toMillis(connectionTimeout);
            return this;
        }

        public Builder writeTimeoutInMillis(long writeTimeout) {
            return writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
        }

        public Builder writeTimeout(long writeTimeout, TimeUnit timeUnit) {
            this.writeTimeoutMillis = timeUnit.toMillis(writeTimeout);
            return this;
        }

        public Builder readTimeoutInMillis(long readTimeout) {
            return readTimeout(readTimeout, TimeUnit.MILLISECONDS);
        }

        public Builder readTimeout(long readTimeout, TimeUnit timeUnit) {
            this.readTimeoutMillis = timeUnit.toMillis(readTimeout);
            return this;
        }

        public HttpConfig build() {
            return new HttpConfig(this);
        }

    }

}
