package com.matchbook.sdk.rest;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class MatchbookSDKClientRest_IT<T extends AbstractClientRest> {

    protected static WireMockServer wireMockServer;

    protected T clientRest;

    private ConnectionManager connectionManager;

    protected abstract T newClientRest(ConnectionManager connectionManager);

    @BeforeClass
    public static void setUpBeforeClass() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8089));
        wireMockServer.start();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        wireMockServer.stop();
    }

    @Before
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

    @After
    public void tearDown() {
        connectionManager.close();
    }

}
