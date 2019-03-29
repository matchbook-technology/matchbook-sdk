package com.matchbook.sdk.core.model.dataobjects;

import com.matchbook.sdk.core.model.dataobjects.auth.User;

public class LoginEnvelope extends Envelope {

    private User user;

    public User getUser() {
        return user;
    }

    private LoginEnvelope(Builder builder) {
        this.user = builder.user;
    }

    public static class Builder {

        private final User user;

        public Builder(User user) {
            this.user = user;
        }

        public LoginEnvelope build() {
            return new LoginEnvelope(this);
        }
    }
}
