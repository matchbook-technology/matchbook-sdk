package com.matchbook.sdk.core.model.dataobjects.auth;

import java.util.Arrays;

public class User {

    private final char[] username;
    private final char[] password;

    private User(User.Builder builder) {
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
        return User.class.getSimpleName() + " {" +
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

        public User build() {
            return new User(this);
        }
    }

}
