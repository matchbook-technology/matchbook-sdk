package com.matchbook.sdk.core.clients.rest.dtos.user;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookResponse;

public class Logout implements MatchbookResponse {

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
    public String toString() {
        return "LogoutResponse{" +
                "sessionToken='" + sessionToken + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
