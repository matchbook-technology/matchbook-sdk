package com.matchbook.sdk.rest.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.MatchbookSDKClientTest;
import com.matchbook.sdk.rest.OffersClient;
import com.matchbook.sdk.rest.OffersClientRest;
import com.matchbook.sdk.rest.dtos.offers.Offer;
import com.matchbook.sdk.rest.dtos.offers.OfferGetRequest;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class OffersClientRestTest extends MatchbookSDKClientTest {

    private final OffersClient offersRestClient;

    public OffersClientRestTest() {
        this.offersRestClient = new OffersClientRest(connectionManager);
    }

    @Test
    public void successfulGetOfferTest() throws InterruptedException {
        stubFor(get(urlPathEqualTo("/edge/rest/v2/offers/382937981320019"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getOfferSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        OfferGetRequest offerGetRequest = new OfferGetRequest.Builder(382937981320019L).build();

        offersRestClient.getOffer(offerGetRequest, new StreamObserver<Offer>() {
            @Override
            public void onNext(Offer offer) {
                assertThat(offer.getId()).isNotNull();
                assertThat(offer.getEventId()).isNotNull();
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override
            public void onError(MatchbookSDKException e) {
                fail(e.getMessage());
            }

        });

        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(await).isTrue();
    }
}
