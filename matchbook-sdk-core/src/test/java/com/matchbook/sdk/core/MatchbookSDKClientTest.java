package com.matchbook.sdk.core;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.matchbook.sdk.core.configs.ClientConfig;
import com.matchbook.sdk.core.configs.ConnectionManager;
import org.junit.Rule;

public abstract class MatchbookSDKClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    protected final ConnectionManager connectionManager;

    protected MatchbookSDKClientTest() {
        ClientConfig clientConfig = new ClientConfig.Builder("login".toCharArray(), "password".toCharArray())
                .sportsUrl("http://localhost:8089/edge/rest")
                .loginUrl("http://localhost:8089/bpapi/rest/security/session")
                .build();

        connectionManager = new ConnectionManager(clientConfig);
    }

}
