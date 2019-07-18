package com.matchbook.sdk.core.dtos.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.core.dtos.RestRequest;

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
    public String resourcePath() {
        return "";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
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
