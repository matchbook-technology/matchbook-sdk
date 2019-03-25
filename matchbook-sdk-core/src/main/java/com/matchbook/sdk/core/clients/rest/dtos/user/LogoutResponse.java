package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Collections;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class LogoutResponse implements RestResponse<LogoutResponse> {

    private String sessionToken;
    private Long userId;
    private String username;

    public String getSessionToken() {
        return sessionToken;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public List<LogoutResponse> getContent() {
        return Collections.singletonList(this);
    }

    @Override
    public String toString() {
        return LogoutResponse.class.getSimpleName() + " {" +
                "sessionToken=" + sessionToken +
                ", userId=" + userId +
                ", username=" + username +
                "}";
    }

}
