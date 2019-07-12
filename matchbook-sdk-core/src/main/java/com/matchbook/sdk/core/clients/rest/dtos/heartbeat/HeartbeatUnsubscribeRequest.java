package com.matchbook.sdk.core.clients.rest.dtos.heartbeat;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.core.clients.rest.dtos.RestRequest;

public class HeartbeatUnsubscribeRequest implements RestRequest {

    @Override
    public String resourcePath() {
        return "v1/heartbeat";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }
}
