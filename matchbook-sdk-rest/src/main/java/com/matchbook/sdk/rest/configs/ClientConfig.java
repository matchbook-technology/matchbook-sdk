package com.matchbook.sdk.rest.configs;

import java.util.Arrays;
import java.util.Objects;

public class ClientConfig {

    private final char[] username;
    private final char[] password;
    private final String sportsUrl;
    private final String loginUrl;
    private final HttpConfig httpConfig;

    private ClientConfig(ClientConfig.Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.sportsUrl = builder.sportsUrl;
        this.loginUrl = builder.loginUrl;
        this.httpConfig = builder.httpConfig;
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

    public HttpConfig getHttpConfig() {
        return httpConfig;
    }

    @Override
    public String toString() {
        return ClientConfig.class.getSimpleName() + " {" +
                "username=" + Arrays.toString(username) +
                ", password=" + Arrays.toString(password) +
                ", sportsUrl=" + sportsUrl +
                ", loginUrl=" + loginUrl +
                ", httpConfig=" + httpConfig +
                "}";
    }

    public static class Builder {

        private final char[] username;
        private final char[] password;

        private String sportsUrl = "https://api.matchbook.com/edge/rest";
        private String loginUrl = "https://api.matchbook.com/bpapi/rest/security/session";

        private HttpConfig httpConfig;

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

        public Builder httpClientConfig(HttpConfig httpConfig) {
            this.httpConfig = httpConfig;
            return this;
        }

        public ClientConfig build() {
            if (Objects.isNull(httpConfig)) {
                HttpConfig defaultHttpConfig = new HttpConfig.Builder().build();
                httpClientConfig(defaultHttpConfig);
            }
            return new ClientConfig(this);
        }
    }
}
