package com.matchbook.sdk.rest.dtos.heartbeat;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.RestRequest;

public class HeartbeatSendRequest implements RestRequest {

    private final Integer timeout;

    private HeartbeatSendRequest(HeartbeatSendRequest.Builder builder) {
        this.timeout = builder.timeout;
    }

    @Override
    public String resourcePath() {
        return "v1/heartbeat";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return HeartbeatSendRequest.class.getSimpleName() + " {" +
            "timeout=" + timeout +
            "}";
    }

    public static class Builder {

        private final Integer timeout;

        public Builder(Integer timeout) {
            this.timeout = timeout;
        }

        public HeartbeatSendRequest build() {
            return new HeartbeatSendRequest(this);
        }

    }

}
