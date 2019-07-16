package com.matchbook.sdk.core.model.dataobjects.user;

public class User {

    private String sessionToken;
    private Long userId;
    private Account account;

    private User(Builder builder) {
        this.sessionToken = builder.sessionToken;
        this.userId = builder.userId;
        this.account = builder.account;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public Long getUserId() {
        return userId;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return User.class.getSimpleName() + "{" +
                "sessionToken=" + sessionToken +
                ", userId=" + userId +
                ", account=" + account +
                "}";
    }

    public static class Builder {
        private final String sessionToken;
        private final Long userId;
        private Account account;

        public Builder(String sessionToken, Long userId) {
            this.sessionToken = sessionToken;
            this.userId = userId;
        }

        public Builder addAccount(Account account) {
            this.account = account;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
