package com.matchbook.sdk.clients.rest.dtos.user;

import com.matchbook.sdk.clients.rest.dtos.MatchbookResponse;

public class LoginResponse implements MatchbookResponse {

    private String sessionToken;
    private long userId;
    private Account account;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "sessionToken='" + sessionToken + '\'' +
                ", userId=" + userId +
                ", account=" + account +
                '}';
    }
}
