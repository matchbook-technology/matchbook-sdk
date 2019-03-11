package com.matchbook.sdk.core.clients.rest.dtos.user;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class Login implements RestResponse {

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
        return Login.class.getSimpleName() + " {" +
                "sessionToken=" + sessionToken +
                ", userId=" + userId +
                ", account=" + account +
                "}";
    }

}
