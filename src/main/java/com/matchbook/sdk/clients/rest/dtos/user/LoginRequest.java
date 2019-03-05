package com.matchbook.sdk.clients.rest.dtos.user;

import java.util.Arrays;

import com.matchbook.sdk.clients.rest.dtos.MatchbookRequest;

public class LoginRequest implements MatchbookRequest {

    private final char[] username;
    private final char[] password;

    public LoginRequest(char[] username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public char[] getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "LoginRequest{" +
                "username=" + Arrays.toString(username) +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}
