package com.matchbook.sdk.core.clients.rest.dtos.user;

import com.matchbook.sdk.core.clients.rest.dtos.RestRequest;

import java.util.Arrays;

public class LoginRequest implements RestRequest {

    private final char[] username;
    private final char[] password;

    private LoginRequest(LoginRequest.Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public char[] getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return LoginRequest.class.getSimpleName() + " {" +
                "username=" + Arrays.toString(username) +
                ", password=" + Arrays.toString(password) +
                "}";
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

}
