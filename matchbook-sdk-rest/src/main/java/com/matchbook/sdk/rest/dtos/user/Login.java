package com.matchbook.sdk.rest.dtos.user;

import java.util.Collections;
import java.util.Set;

import com.matchbook.sdk.rest.dtos.RestResponse;

public class Login implements RestResponse<Login> {

    private String sessionToken;
    private Long userId;
    private Account account;

    public String getSessionToken() {
        return sessionToken;
    }

    public Long getUserId() {
        return userId;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public Set<Login> getContent() {
        return Collections.singleton(this);
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
