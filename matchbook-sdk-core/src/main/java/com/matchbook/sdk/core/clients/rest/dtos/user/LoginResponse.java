package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Collections;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

public class LoginResponse implements RestResponse<Login> {

    private Login login;

    public Login getLogin() {
        return login;
    }

    @Override
    public List<Login> getContent() {
        return Collections.singletonList(login);
    }

    @Override
    public String toString() {
        return LoginResponse.class.getSimpleName() + " {" +
                "login=" + login +
                "}";
    }

}
