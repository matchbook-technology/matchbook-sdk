package com.matchbook.sdk;

public class ClientConfig {

    private String url;
    private String loginUrl;
    private char[] username;
    private char[] password;

    private ClientConfig() {
    }

    public static class Builder {
        private String url;
        private String loginUrl;
        private char[] username;
        private char[] password;

        public Builder(char[] username, char[] password) {
            this.username = username;
            this.password = password;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder loginUrl(String loginUrl) {
            this.loginUrl = loginUrl;
            return this;
        }

        public ClientConfig build() {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.url = url;
            clientConfig.username = username;
            clientConfig.password = password;
            clientConfig.loginUrl = loginUrl;
            return clientConfig;
        }
    }

    public String getUrl() {
        return url;
    }

    public char[] getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    public String getLoginUrl() {
        return loginUrl;
    }
}
