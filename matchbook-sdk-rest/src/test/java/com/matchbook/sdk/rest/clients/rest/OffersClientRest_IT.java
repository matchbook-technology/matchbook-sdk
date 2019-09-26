package com.matchbook.sdk.rest.clients.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Collections;
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
import com.matchbook.sdk.rest.dtos.offers.OfferDeleteRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferEditsGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferPostRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferPutRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersDeleteRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersPostRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersPutRequest;
import com.matchbook.sdk.rest.dtos.offers.Position;
import com.matchbook.sdk.rest.dtos.offers.PositionsRequest;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.Side;
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
        String url = "/edge/rest/v2/offers/382937981320019";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getOfferSuccessfulResponse.json")));

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

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void getOffersTest() throws InterruptedException {
        String url = "/edge/rest/v2/offers";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getOffersSuccessfulResponse.json")));

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

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void submitOfferTest() throws InterruptedException {
        String url = "/edge/rest/v2/offers";
        wireMockServer.stubFor(post(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/postOffersSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        OfferPostRequest offerRequest = new OfferPostRequest
                .Builder(401525949430009L, Side.BACK, 2.4, new BigDecimal("5"))
                .build();
        OffersPostRequest offersPostRequest = new OffersPostRequest
                .Builder(OddsType.DECIMAL, ExchangeType.BACK_LAY, Collections.singletonList(offerRequest))
                .build();

        offersClientRest.submitOffers(offersPostRequest, new StreamObserver<Offer>() {

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

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void editOfferTest() throws InterruptedException {
        String url = "/edge/rest/v2/offers/925183846730025";
        wireMockServer.stubFor(put(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/putOfferSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        OfferPutRequest offerPutRequest = new OfferPutRequest
                .Builder(925183846730025L, 3.0, new BigDecimal("100"))
                .build();

        offersClientRest.editOffer(offerPutRequest, new StreamObserver<Offer>() {

            @Override
            public void onNext(Offer offer) {
                verifyOffer(offer);
                verifyOfferEdit(offer.getOfferEdit());
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

        wireMockServer.verify(putRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void editOffersTest() throws InterruptedException {
        String url = "/edge/rest/v2/offers";
        wireMockServer.stubFor(put(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/putOffersSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        OfferPutRequest offerPutRequest = new OfferPutRequest
                .Builder(925183846730025L, 3.0, new BigDecimal("100"))
                .build();
        OffersPutRequest offersPutRequest = new OffersPutRequest
                .Builder(Collections.singletonList(offerPutRequest))
                .build();

        offersClientRest.editOffers(offersPutRequest, new StreamObserver<Offer>() {

            @Override
            public void onNext(Offer offer) {
                verifyOffer(offer);
                verifyOfferEdit(offer.getOfferEdit());
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

        wireMockServer.verify(putRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void deleteOfferTest() throws InterruptedException {
        String url = "/edge/rest/v2/offers/413775799780013";
        wireMockServer.stubFor(delete(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/deleteOfferSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        OfferDeleteRequest offerDeleteRequest = new OfferDeleteRequest.Builder(413775799780013L).build();

        offersClientRest.cancelOffer(offerDeleteRequest, new StreamObserver<Offer>() {

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

        wireMockServer.verify(deleteRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void deleteOffersTest() throws InterruptedException {
        String url = "/edge/rest/v2/offers";
        wireMockServer.stubFor(delete(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/deleteOffersSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        OffersDeleteRequest offersDeleteRequest = new OffersDeleteRequest.Builder().build();

        offersClientRest.cancelOffers(offersDeleteRequest, new StreamObserver<Offer>() {

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

        wireMockServer.verify(deleteRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
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
        String url = "/edge/rest/v2/offers/925183846730025/offer-edits/925184068850125";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getOfferEditSuccessfulResponse.json")));

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

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void getOfferEditsTest() throws InterruptedException {
        String url = "/edge/rest/v2/offers/925183846730025/offer-edits";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getOfferEditsSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(3);

        OfferEditsGetRequest offerEditsGetRequest = new OfferEditsGetRequest.Builder(925183846730025L).build();

        offersClientRest.getOfferEdits(offerEditsGetRequest, new StreamObserver<OfferEdit>() {

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

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyOfferEdit(OfferEdit offerEdit) {
        assertNotNull(offerEdit);
        assertNotNull(offerEdit.getId());
        assertNotNull(offerEdit.getOfferId());
        assertNotNull(offerEdit.getStatus());
        assertNotNull(offerEdit.getOddsType());
        assertNotNull(offerEdit.getOddsBefore());
        assertNotNull(offerEdit.getOddsAfter());
        assertNotNull(offerEdit.getStakeBefore());
        assertNotNull(offerEdit.getStakeAfter());
    }

    @Test
    public void getAggregatedMatchedBetsTest() throws InterruptedException {
        String url = "/edge/rest/bets/matched/aggregated";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getAggregatedMatchedBetsResponse.json")));

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

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void getCancelledMatchedBetsTest() throws InterruptedException {
        String url = "/edge/rest/bets";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withQueryParam("status", new EqualToPattern("CANCELLED"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getCancelledMatchedBetsResponse.json")));

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

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void getPositionsTest() throws InterruptedException {
        String url = "/edge/rest/account/positions";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getPositionsResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(4);

        PositionsRequest positionsRequest = new PositionsRequest.Builder().build();

        offersClientRest.getPositions(positionsRequest, new StreamObserver<Position>() {

            @Override
            public void onNext(Position position) {
                assertNotNull(position);
                assertNotNull(position.getEventId());
                assertNotNull(position.getMarketId());
                assertNotNull(position.getRunnerId());
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

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

}
