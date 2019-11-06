package com.matchbook.sdk.rest.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestResponseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogoutTest extends RestResponseTest<Logout> {

    @Override
    protected Logout newResponse() {
        return new Logout();
    }

    @Test
    @DisplayName("Set and get user ID")
    void userIdTest() {
        Long userId = 4242L;
        unit.setUserId(userId);
        assertThat(unit.getUserId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("Set and get session token")
    void sessionTokenTest() {
        String sessionToken = "000000_8beb227cee5689";
        unit.setSessionToken(sessionToken);
        assertThat(unit.getSessionToken()).isEqualTo(sessionToken);
    }

    @Test
    @DisplayName("Set and get username")
    void usernameTest() {
        String username = "john_doe";
        unit.setUsername(username);
        assertThat(unit.getUsername()).isEqualTo(username);
    }

}
