package com.matchbook.sdk.clients.rest.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matchbook.sdk.clients.rest.dtos.MatchbookResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogoutResponse implements MatchbookResponse {

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
