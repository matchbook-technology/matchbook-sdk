package com.matchbook.sdk.clients.rest.dtos.user;

import com.matchbook.sdk.clients.rest.dtos.MatchbookRequest;

public class LoginRequest implements MatchbookRequest {

    private final String username;
    private final char[] password;

    public LoginRequest(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }
}
