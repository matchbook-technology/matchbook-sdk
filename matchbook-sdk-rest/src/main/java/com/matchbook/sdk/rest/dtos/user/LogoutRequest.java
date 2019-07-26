package com.matchbook.sdk.rest.dtos.user;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.RestRequest;

public class LogoutRequest implements RestRequest {

    private LogoutRequest() {
    }

    @Override
    public String resourcePath() {
        return "";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return LogoutRequest.class.getSimpleName();
    }

    public static class Builder {

        public LogoutRequest build() {
            return new LogoutRequest();
        }
    }

}
