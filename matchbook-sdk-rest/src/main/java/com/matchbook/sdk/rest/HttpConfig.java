package com.matchbook.sdk.rest;

import java.util.concurrent.TimeUnit;

public class HttpConfig {

    private final long connectionTimeout;
    private final long writeTimeout;
    private final long readTimeout;

    public HttpConfig(HttpConfig.Builder builder) {
        this.connectionTimeout = builder.connectionTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    @Override
    public String toString() {
        return HttpConfig.class.getSimpleName() + " {" +
                "connectionTimeout=" + connectionTimeout +
                ", writeTimeout=" + writeTimeout +
                ", readTimeout=" + readTimeout +
                "}";
    }

    public static class Builder {

        private long connectionTimeout = TimeUnit.SECONDS.toMillis(5);
        private long writeTimeout = TimeUnit.SECONDS.toMillis(15);
        private long readTimeout = TimeUnit.SECONDS.toMillis(30);

        public Builder connectionTimeout(long connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public Builder writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public HttpConfig build() {
            return new HttpConfig(this);
        }
    }

}
