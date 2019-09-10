package com.matchbook.sdk.rest.clients.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.MatchbookSDKClientRest_IT;
import com.matchbook.sdk.rest.OffersClientRest;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBet;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBetsRequest;
import com.matchbook.sdk.rest.dtos.offers.CancelledMatchedBetsRequest;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;
import com.matchbook.sdk.rest.dtos.offers.MatchedBetStatus;
import com.matchbook.sdk.rest.dtos.offers.Offer;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersGetRequest;
import org.junit.Before;
import org.junit.Test;

public class OffersClientRest_IT extends MatchbookSDKClientRest_IT {

    private OffersClientRest offersClientRest;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.offersClientRest = new OffersClientRest(connectionManager);
    }

    @Test
    public void getOfferTest() throws InterruptedException {
        wireMockServer.stubFor(get(urlPathEqualTo("/edge/rest/v2/offers/382937981320019"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getOfferSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        OfferGetRequest offerGetRequest = new OfferGetRequest.Builder(382937981320019L).build();

        offersClientRest.getOffer(offerGetRequest, new StreamObserver<Offer>() {

            @Override
            public void onNext(Offer offer) {
                verifyOffer(offer);
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

    @Test
    public void getOffersTest() throws InterruptedException {
        wireMockServer.stubFor(get(urlPathEqualTo("/edge/rest/v2/offers"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getOffersSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        OffersGetRequest offersGetRequest = new OffersGetRequest.Builder().build();

        offersClientRest.getOffers(offersGetRequest, new StreamObserver<Offer>() {

            @Override
            public void onNext(Offer offer) {
                verifyOffer(offer);
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

    private void verifyOffer(Offer offer) {
        assertNotNull(offer);
        assertNotNull(offer.getId());
        assertNotNull(offer.getEventId());
        assertNotNull(offer.getMarketId());
        assertNotNull(offer.getRunnerId());
        assertNotNull(offer.getStatus());
        assertNotNull(offer.getSide());
        assertNotNull(offer.getOddsType());
        assertNotNull(offer.getOdds());
        assertNotNull(offer.getStake());
    }

    @Test
    public void getOfferEditTest() throws InterruptedException {
        wireMockServer.stubFor(get(urlPathEqualTo("/edge/rest/v2/offers/925183846730025/offer-edits/925184068850125"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getOfferEditSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        OfferEditGetRequest offerEditGetRequest = new OfferEditGetRequest.Builder(925184068850125L, 925183846730025L).build();

        offersClientRest.getOfferEdit(offerEditGetRequest, new StreamObserver<OfferEdit>() {

            @Override
            public void onNext(OfferEdit offerEdit) {
                verifyOfferEdit(offerEdit);
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

    private void verifyOfferEdit(OfferEdit offerEdit) {
        assertNotNull(offerEdit);
        assertNotNull(offerEdit.getId());
        assertNotNull(offerEdit.getOfferId());
        assertNotNull(offerEdit.getRunnerId());
        assertNotNull(offerEdit.getStatus());
        assertNotNull(offerEdit.getOddsType());
        assertNotNull(offerEdit.getOddsBefore());
        assertNotNull(offerEdit.getOddsAfter());
        assertNotNull(offerEdit.getStakeBefore());
        assertNotNull(offerEdit.getStakeAfter());
    }

    @Test
    public void getAggregatedMatchedBetsTest() throws InterruptedException {
        wireMockServer.stubFor(get(urlPathEqualTo("/edge/rest/bets/matched/aggregated"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getAggregatedMatchedBetsResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(3);

        AggregatedMatchedBetsRequest aggregatedMatchedBetsRequest = new AggregatedMatchedBetsRequest.Builder().build();

        offersClientRest.getAggregatedMatchedBets(aggregatedMatchedBetsRequest, new StreamObserver<AggregatedMatchedBet>() {

            @Override
            public void onNext(AggregatedMatchedBet aggregatedMatchedBet) {
                assertNotNull(aggregatedMatchedBet);
                assertNotNull(aggregatedMatchedBet.getEventId());
                assertNotNull(aggregatedMatchedBet.getMarketId());
                assertNotNull(aggregatedMatchedBet.getRunnerId());
                assertNotNull(aggregatedMatchedBet.getSide());
                assertNotNull(aggregatedMatchedBet.getOddsType());
                assertNotNull(aggregatedMatchedBet.getOdds());
                assertNotNull(aggregatedMatchedBet.getStake());
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

    @Test
    public void getCancelledMatchedBetsTest() throws InterruptedException {
        wireMockServer.stubFor(get(urlPathEqualTo("/edge/rest/bets"))
                .withQueryParam("status", new EqualToPattern("CANCELLED"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/getCancelledMatchedBetsResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        CancelledMatchedBetsRequest cancelledMatchedBetsRequest = new CancelledMatchedBetsRequest.Builder().build();

        offersClientRest.getCancelledMatchedBets(cancelledMatchedBetsRequest, new StreamObserver<MatchedBet>() {

            @Override
            public void onNext(MatchedBet matchedBet) {
                assertNotNull(matchedBet);
                assertNotNull(matchedBet.getOfferId());
                assertNotNull(matchedBet.getCurrency());
                assertNotNull(matchedBet.getOddsType());
                assertNotNull(matchedBet.getOdds());
                assertNotNull(matchedBet.getStake());
                assertNotNull(matchedBet.getCommission());
                assertEquals(MatchedBetStatus.CANCELLED, matchedBet.getStatus());
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
