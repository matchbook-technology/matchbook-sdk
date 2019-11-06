package com.matchbook.sdk.rest.configs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.configs.ClientConfig;
import com.matchbook.sdk.rest.configs.HttpConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClientConfigTest {

    private char[] username;
    private char[] password;
    private String sportsUrl;
    private String loginUrl;
    private HttpConfig httpConfig;

    private ClientConfig unit;
    private ClientConfig defaultUnit;

    @BeforeEach
    void setUp() {
        username = "john_doe".toCharArray();
        password = "secret".toCharArray();
        sportsUrl = "https://matchbook.example.com/sports";
        loginUrl = "https://matchbook.example.com/login";
        httpConfig = mock(HttpConfig.class);

        unit = new ClientConfig.Builder(username, password)
                .sportsUrl(sportsUrl)
                .loginUrl(loginUrl)
                .httpClientConfig(httpConfig)
                .build();
        defaultUnit = new ClientConfig.Builder(username, password).build();
    }

    @Test
    @DisplayName("Check username")
    void usernameTest() {
        assertThat(unit.getUsername()).isEqualTo(username)
                .isEqualTo(defaultUnit.getUsername());
    }

    @Test
    @DisplayName("Check password")
    void passwordTest() {
        assertThat(unit.getPassword()).isEqualTo(password)
                .isEqualTo(defaultUnit.getPassword());
    }

    @Test
    @DisplayName("Check sports URL")
    void sportsUrlTest() {
        assertThat(unit.getSportsUrl()).isEqualTo(sportsUrl);
        assertThat(defaultUnit.getSportsUrl()).isEqualTo("https://api.matchbook.com/edge/rest");
    }

    @Test
    @DisplayName("Check login URL")
    void loginUrlTest() {
        assertThat(unit.getLoginUrl()).isEqualTo(loginUrl);
        assertThat(defaultUnit.getLoginUrl()).isEqualTo("https://api.matchbook.com/bpapi/rest/security/session");
    }

    @Test
    @DisplayName("Check HTTP configuration")
    void httpConfigTest() {
        assertThat(unit.getHttpConfig()).isEqualTo(httpConfig);
        assertThat(defaultUnit.getHttpConfig()).isNotNull();
    }

    @Test
    @DisplayName("Object description")
    void toStringTest() {
        assertThat(unit.toString()).startsWith(unit.getClass().getSimpleName());
    }

}
