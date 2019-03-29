package com.matchbook.sdk.core.model.dataobjects.auth;

public class Login {

    private String sessionToken;
    private Long userId;
    private Account account;

    public String getSessionToken() {
        return sessionToken;
    }

    public long getUserId() {
        return userId;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return Login.class.getSimpleName() + " {" +
                "sessionToken=" + sessionToken +
                ", userId=" + userId +
                ", account=" + account +
                "}";
    }

}
