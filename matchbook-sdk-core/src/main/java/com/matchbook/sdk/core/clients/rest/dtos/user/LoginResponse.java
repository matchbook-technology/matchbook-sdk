package com.matchbook.sdk.core.clients.rest.dtos.user;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookResponse;

public class LoginResponse implements MatchbookResponse {

    private String sessionToken;
    private Long userId;
    private Account account;

    public String getSessionToken() {
        return sessionToken;
    }

    public long getUserId() {
        return userId;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return LoginResponse.class.getSimpleName() + " {" +
                "sessionToken=" + sessionToken +
                ", userId=" + userId +
                ", account=" + account +
                "}";
    }

}
