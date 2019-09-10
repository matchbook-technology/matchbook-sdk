package com.matchbook.sdk.rest.clients.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.MatchbookSDKClientRest_IT;
import com.matchbook.sdk.rest.PricesClientRest;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.PricesRequest;
import org.junit.Before;
import org.junit.Test;

public class PricesClientRest_IT extends MatchbookSDKClientRest_IT {

    private PricesClientRest pricesClientRest;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.pricesClientRest = new PricesClientRest(connectionManager);
    }

    @Test
    public void getSportsTest() throws InterruptedException {
        String testUrl = "/edge/rest/events/395729780570010/markets/395729860260010/runners/395729860800010/prices";
        wireMockServer.stubFor(get(urlPathEqualTo(testUrl))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/prices/getPricesSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        PricesRequest pricesRequest = new PricesRequest
                .Builder(395729780570010L, 395729860260010L, 395729860800010L)
                .build();

        pricesClientRest.getPrices(pricesRequest, new StreamObserver<Price>() {

            @Override
            public void onNext(Price price) {
                assertNotNull(price);
                assertNotNull(price.getOddsType());
                assertNotNull(price.getOdds());
                countDownLatch.countDown();
            }

            @Override
            public void onError(MatchbookSDKException e) {
                fail();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });

        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }

}
