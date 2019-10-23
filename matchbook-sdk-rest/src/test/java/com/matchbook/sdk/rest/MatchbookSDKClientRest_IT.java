package com.matchbook.sdk.rest;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class MatchbookSDKClientRest_IT<T extends BaseClientRest> {

    protected static WireMockServer wireMockServer;

    protected T clientRest;

    private ConnectionManager connectionManager;

    protected abstract T newClientRest(ConnectionManager connectionManager);

    @BeforeAll
    public static void setUpBeforeClass() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8089));
        wireMockServer.start();
    }

    @AfterAll
    public static void tearDownAfterClass() {
        wireMockServer.stop();
    }

    @BeforeEach
    public void setUp() {
        ClientConfig clientConfig = new ClientConfig.Builder("login".toCharArray(), "password".toCharArray())
                .sportsUrl("http://localhost:8089/edge/rest")
                .loginUrl("http://localhost:8089/bpapi/rest/security/session")
                .build();
        connectionManager = new ConnectionManager.Builder(clientConfig)
                .autoManageSession(false)
                .build();
        clientRest = newClientRest(connectionManager);
    }

    @AfterEach
    public void tearDown() {
        connectionManager.close();
    }

}
