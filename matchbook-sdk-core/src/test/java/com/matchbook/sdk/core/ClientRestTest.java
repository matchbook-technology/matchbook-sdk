package com.matchbook.sdk.core;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.core.configs.ClientConfig;
import com.matchbook.sdk.core.services.AuthService;
import org.junit.Before;
import org.junit.Test;

public class ClientRestTest {

    private ClientConfig clientConfig;

    @Before
    public void setUp() throws Exception {
        this.clientConfig = new ClientConfig.Builder("user".toCharArray(),
                "password".toCharArray()).build();
    }

    @Test
    public void reuseClientTest() {
        Client unit = new ClientRest(clientConfig);

        AuthService authService = unit.getAuthService();
        AuthService authService2 = unit.getAuthService();

        assertThat(authService).isEqualTo(authService2);
    }
}
