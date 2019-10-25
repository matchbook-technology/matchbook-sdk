package com.matchbook.sdk.rest.dtos.heartbeat;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.RestRequest;

public class HeartbeatDeleteRequest implements RestRequest {

    private HeartbeatDeleteRequest() {
        // not visible
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
        return HeartbeatDeleteRequest.class.getSimpleName();
    }

    public static class Builder {

        public HeartbeatDeleteRequest build() {
            return new HeartbeatDeleteRequest();
        }

    }

}
