package com.matchbook.sdk.rest.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginRequestTest extends RestRequestTest<LoginRequest> {

    private char[] username;
    private char[] password;

    @Override
    @BeforeEach
    protected void setUp() {
        username = "john_doe".toCharArray();
        password = "secret".toCharArray();

        super.setUp();
    }

    @Override
    protected LoginRequest newRequest() {
        return new LoginRequest.Builder(username, password).build();
    }

    @Test
    @DisplayName("Check username")
    void usernameTest() {
        char[] actualUsername = unit.getUsername();
        assertThat(actualUsername).isEqualTo(username);
    }

    @Test
    @DisplayName("Check password")
    void passwordTest() {
        char[] actualPassword = unit.getPassword();
        assertThat(actualPassword).isEqualTo(password);
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        assertThat(unit.resourcePath()).isEmpty();
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).isEmpty();
    }

}
