package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Collections;
import java.util.Set;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class Logout implements RestResponse<Logout> {

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
