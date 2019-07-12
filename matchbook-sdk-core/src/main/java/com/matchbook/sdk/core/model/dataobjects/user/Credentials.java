package com.matchbook.sdk.core.model.dataobjects.user;

import java.util.Arrays;

public class Credentials {

    private final char[] username;
    private final char[] password;

    private Credentials(Credentials.Builder builder) {
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
        return Credentials.class.getSimpleName() + "{" +
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

        public Credentials build() {
            return new Credentials(this);
        }
    }

}
