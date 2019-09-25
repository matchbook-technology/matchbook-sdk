package com.matchbook.sdk.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.anyRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class MatchbookSDKClientRest_IT {

    protected static WireMockServer wireMockServer;

    protected ConnectionManager connectionManager;

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
    }

    @After
    public void tearDown() {
        wireMockServer.verify(anyRequestedFor(anyUrl())
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

}
