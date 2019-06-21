package com.matchbook.sdk.core.configs;

import java.util.Arrays;

public class ClientConfig {

    private final char[] username;
    private final char[] password;
    private final String sportsUrl;
    private final String loginUrl;

    private ClientConfig(ClientConfig.Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.sportsUrl = builder.sportsUrl;
        this.loginUrl = builder.loginUrl;
    }

    public char[] getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    public String getSportsUrl() {
        return sportsUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    @Override
    public String toString() {
        return ClientConfig.class.getSimpleName() + "{" +
                "username=" + Arrays.toString(username) +
                ", password='" + Arrays.toString(password) +
                ", buildSportsUrl=" + sportsUrl +
                ", buildLoginUrl=" + loginUrl +
                "}";
    }

    public static class Builder {

        private final char[] username;
        private final char[] password;

        private String sportsUrl = "https://api.matchbook.com/edge/rest";
        private String loginUrl = "https://api.matchbook.com/bpapi/rest/security/session";

        public Builder(char[] username, char[] password) {
            this.username = username;
            this.password = password;
        }

        public Builder sportsUrl(String sportsUrl) {
            this.sportsUrl = sportsUrl;
            return this;
        }

        public Builder loginUrl(String loginUrl) {
            this.loginUrl = loginUrl;
            return this;
        }

        public ClientConfig build() {
            return new ClientConfig(this);
        }
    }
}
