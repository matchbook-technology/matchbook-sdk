package com.matchbook.sdk.stream.model.dataobjects;

import com.matchbook.sdk.stream.model.dataobjects.user.User;

public class LoginEnvelope implements Envelope {

    private final User user;

    private LoginEnvelope(Builder builder) {
        this.user = builder.user;
    }

    public User getUser() {
        return user;
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
