package com.matchbook.sdk.rest.dtos.heartbeat;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.RestRequest;

public class HeartbeatPostRequest implements RestRequest {

    private final Integer timeout;

    private HeartbeatPostRequest(HeartbeatPostRequest.Builder builder) {
        this.timeout = builder.timeout;
    }

    @Override
    public String resourcePath() {
        return "v1/heartbeat";
    }

    public Integer getTimeout() {
        return timeout;
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return HeartbeatPostRequest.class.getSimpleName() + " {" +
            "timeout=" + timeout +
            "}";
    }

    public static class Builder {

        private final Integer timeout;

        public Builder(Integer timeout) {
            this.timeout = timeout;
        }

        public HeartbeatPostRequest build() {
            return new HeartbeatPostRequest(this);
        }

    }

}
