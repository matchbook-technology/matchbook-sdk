package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Collections;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class LoginResponse implements RestResponse<LoginResponse> {

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
    public List<LoginResponse> getContent() {
        return Collections.singletonList(this);
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
