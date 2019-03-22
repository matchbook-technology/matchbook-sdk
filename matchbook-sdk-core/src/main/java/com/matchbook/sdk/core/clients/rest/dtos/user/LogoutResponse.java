package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Collections;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class LogoutResponse implements RestResponse<Logout> {

    private Logout logout;

    public Logout getLogout() {
        return logout;
    }

    @Override
    public List<Logout> getContent() {
        return Collections.singletonList(logout);
    }

    @Override
    public String toString() {
        return LogoutResponse.class.getSimpleName() + " {" +
                "logout=" + logout +
                "}";
    }

}
