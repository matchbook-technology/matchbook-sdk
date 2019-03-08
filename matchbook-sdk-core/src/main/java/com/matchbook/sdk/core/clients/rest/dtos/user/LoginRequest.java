package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Arrays;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookRequest;

public class LoginRequest implements MatchbookRequest {

    private final char[] username;
    private final char[] password;

    private LoginRequest(LoginRequest.Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public static class Builder {
        private final char[] username;
        private final char[] password;

        public Builder(char[] username, char[] password) {
            this.username = username;
            this.password = password;
        }

        public LoginRequest build(){
            return new LoginRequest(this);
        }
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
