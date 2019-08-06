package com.matchbook.sdk.rest.dtos.heartbeat;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.RestRequest;

public class HeartbeatUnsubscribeRequest implements RestRequest {

    private HeartbeatUnsubscribeRequest() {
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
        return HeartbeatUnsubscribeRequest.class.getSimpleName();
    }

    public static class Builder {

        public HeartbeatUnsubscribeRequest build() {
            return new HeartbeatUnsubscribeRequest();
        }

    }
}
