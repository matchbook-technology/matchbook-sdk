package com.matchbook.sdk.core;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.core.services.Auth;
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
        Client client = new ClientRest(clientConfig);

        Auth authClient = client.getAuthClient();
        Auth authClient2 = client.getAuthClient();


        assertThat(authClient).isEqualTo(authClient2);
    }
}
