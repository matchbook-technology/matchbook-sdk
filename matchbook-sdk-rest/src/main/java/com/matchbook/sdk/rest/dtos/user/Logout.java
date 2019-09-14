package com.matchbook.sdk.rest.dtos.user;

import com.matchbook.sdk.rest.dtos.RestResponse;

import java.util.Collections;
import java.util.Set;

public class Logout implements RestResponse<Logout> {

    private String sessionToken;
    private Long userId;
    private String username;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Set<Logout> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return Logout.class.getSimpleName() + " {" +
                "sessionToken=" + sessionToken +
                ", userId=" + userId +
                ", username=" + username +
                "}";
    }

}
