package com.matchbook.sdk.rest;

public class HttpConfig {

    private final int connectionTimeout;
    private final int writeTimeout;
    private final int readTimeout;

    public HttpConfig(HttpConfig.Builder builder) {
        this.connectionTimeout = builder.connectionTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public int getReadTimeout() {
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

        private int connectionTimeout = 15_000;
        private int writeTimeout = 15_000;
        private int readTimeout = 15_000;

        public Builder connectionTimout(int connectionTimout) {
            this.connectionTimeout = connectionTimout;
            return this;
        }

        public Builder writeTimout(int writeTimout) {
            this.writeTimeout = writeTimout;
            return this;
        }

        public Builder readTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public HttpConfig build() {
            return new HttpConfig(this);
        }
    }
}
