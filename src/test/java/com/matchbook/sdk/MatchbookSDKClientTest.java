package com.matchbook.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.matchbook.sdk.configs.ClientConnectionManager;
import com.squareup.okhttp.OkHttpClient;
import org.junit.Rule;

public abstract class MatchbookSDKClientTest {

    //TODO: Replace for dynamic port
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    protected final ClientConnectionManager clientConnectionManager;

    public MatchbookSDKClientTest() {
        ClientConfig clientConfig = new ClientConfig.Builder("login".toCharArray(), "password".toCharArray())
                .loginUrl("http://localhost:8089/bpapi/rest/security/session")
                .url("http://localhost:8089")
                .build();

        clientConnectionManager = new ClientConnectionManager(clientConfig,
                new OkHttpClient(),
                new ObjectMapper());
    }
}
