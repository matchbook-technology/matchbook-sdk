package com.matchbook.sdk.rest;

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
import com.matchbook.sdk.rest.dtos.offers.OfferStatus;
import com.matchbook.sdk.rest.dtos.offers.OffersDeleteRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersPostRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersPutRequest;
import com.matchbook.sdk.rest.dtos.offers.Position;
import com.matchbook.sdk.rest.dtos.offers.PositionsRequest;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.Side;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import org.junit.jupiter.api.Test;

public class OffersClientRest_IT extends MatchbookSDKClientRest_IT<OffersClientRest> {

    @Override
    protected OffersClientRest newClientRest(ConnectionManager connectionManager) {
        return new OffersClientRest(connectionManager);
    }

    @Test
    void getOfferTest() {
        String url = "/edge/rest/v2/offers/382937981320019";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getOfferSuccessfulResponse.json")));

        OfferGetRequest offerGetRequest = new OfferGetRequest.Builder(382937981320019L).build();
        ResponseStreamObserver<Offer> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyOffer);
        clientRest.getOffer(offerGetRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void getOffersTest() {
        String url = "/edge/rest/v2/offers";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getOffersSuccessfulResponse.json")));

        OffersGetRequest offersGetRequest = new OffersGetRequest.Builder().build();
        ResponseStreamObserver<Offer> streamObserver = new SuccessfulResponseStreamObserver<>(2, this::verifyOffer);
        clientRest.getOffers(offersGetRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void submitOffersTest() {
        String url = "/edge/rest/v2/offers";
        wireMockServer.stubFor(post(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/postOffersSuccessfulResponse.json")));

        OfferPostRequest offerRequest = new OfferPostRequest
                .Builder(401525949430009L, Side.BACK, 2.4, new BigDecimal("5"))
                .build();
        OffersPostRequest offersPostRequest = new OffersPostRequest
                .Builder(OddsType.DECIMAL, ExchangeType.BACK_LAY, Collections.singletonList(offerRequest))
                .build();
        ResponseStreamObserver<Offer> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyOffer);
        clientRest.submitOffers(offersPostRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void submitOffersPartiallyFailedTest() {
        String url = "/edge/rest/v2/offers";
        wireMockServer.stubFor(post(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/postOffersPartiallyFailedResponse.json")));

        OfferPostRequest offerRequest1 = new OfferPostRequest
                .Builder(1227187302310017L, Side.BACK, 11d, new BigDecimal("-2"))
                .build();
        OfferPostRequest offerRequest2 = new OfferPostRequest
                .Builder(1227187302350017L, Side.BACK, 1.2d, new BigDecimal("1"))
                .build();
        OffersPostRequest offersPostRequest = new OffersPostRequest
                .Builder(OddsType.DECIMAL, ExchangeType.BACK_LAY, Arrays.asList(offerRequest1, offerRequest2))
                .build();
        ResponseStreamObserver<Offer> streamObserver = new SuccessfulResponseStreamObserver<>(2, offer -> {
            assertThat(offer).isNotNull();
            assertThat(offer.getRunnerId()).isNotNull();
            assertThat(offer.getStatus()).isNotNull();
            assertThat(offer.getSide()).isNotNull();
            assertThat(offer.getOddsType()).isNotNull();
            assertThat(offer.getOdds()).isNotNull();
            assertThat(offer.getStake()).isNotNull();
            if (offer.getStatus() == OfferStatus.FAILED) {
                assertThat(offer.getErrors()).isNotNull();
                assertThat(offer.getErrors().getErrors()).isNotEmpty();
            }
        });
        clientRest.submitOffers(offersPostRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void editOfferTest() {
        String url = "/edge/rest/v2/offers/925183846730025";
        wireMockServer.stubFor(put(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/putOfferSuccessfulResponse.json")));

        OfferPutRequest offerPutRequest = new OfferPutRequest
                .Builder(925183846730025L, 3.0, new BigDecimal("100"))
                .build();
        ResponseStreamObserver<Offer> streamObserver = new SuccessfulResponseStreamObserver<>(1, offer -> {
            verifyOffer(offer);
            verifyOfferEdit(offer.getOfferEdit());
        });
        clientRest.editOffer(offerPutRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(putRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void editOffersTest() {
        String url = "/edge/rest/v2/offers";
        wireMockServer.stubFor(put(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/putOffersSuccessfulResponse.json")));

        OfferPutRequest offerPutRequest = new OfferPutRequest
                .Builder(925183846730025L, 3.0, new BigDecimal("100"))
                .build();
        OffersPutRequest offersPutRequest = new OffersPutRequest
                .Builder(Collections.singletonList(offerPutRequest))
                .build();
        ResponseStreamObserver<Offer> streamObserver = new SuccessfulResponseStreamObserver<>(1, offer -> {
            verifyOffer(offer);
            verifyOfferEdit(offer.getOfferEdit());
        });
        clientRest.editOffers(offersPutRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(putRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void editOffersPartiallyFailedTest() {
        String url = "/edge/rest/v2/offers";
        wireMockServer.stubFor(put(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/putOffersPartiallyFailedResponse.json")));

        OfferPutRequest offerPutRequest = new OfferPutRequest
                .Builder(1239207305820024L, 1.238, new BigDecimal("-2"))
                .build();
        OffersPutRequest offersPutRequest = new OffersPutRequest
                .Builder(Collections.singletonList(offerPutRequest))
                .build();
        ResponseStreamObserver<Offer> streamObserver = new SuccessfulResponseStreamObserver<>(1, offer -> {
            assertThat(offer).isNotNull();
            assertThat(offer.getOfferEdit()).isNotNull();
            assertThat(offer.getOfferEdit().getErrors()).isNotNull();
            assertThat(offer.getOfferEdit().getErrors().getErrors()).isNotEmpty();
        });
        clientRest.editOffers(offersPutRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(putRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void deleteOfferTest() {
        String url = "/edge/rest/v2/offers/413775799780013";
        wireMockServer.stubFor(delete(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/deleteOfferSuccessfulResponse.json")));

        OfferDeleteRequest offerDeleteRequest = new OfferDeleteRequest.Builder(413775799780013L).build();
        ResponseStreamObserver<Offer> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyOffer);
        clientRest.cancelOffer(offerDeleteRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(deleteRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void deleteOffersTest() {
        String url = "/edge/rest/v2/offers";
        wireMockServer.stubFor(delete(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/deleteOffersSuccessfulResponse.json")));

        OffersDeleteRequest offersDeleteRequest = new OffersDeleteRequest.Builder().build();
        ResponseStreamObserver<Offer> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyOffer);
        clientRest.cancelOffers(offersDeleteRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(deleteRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyOffer(Offer offer) {
        assertThat(offer).isNotNull();
        assertThat(offer.getId()).isNotNull();
        assertThat(offer.getEventId()).isNotNull();
        assertThat(offer.getMarketId()).isNotNull();
        assertThat(offer.getRunnerId()).isNotNull();
        assertThat(offer.getStatus()).isNotNull();
        assertThat(offer.getSide()).isNotNull();
        assertThat(offer.getOddsType()).isNotNull();
        assertThat(offer.getOdds()).isNotNull();
        assertThat(offer.getStake()).isNotNull();
    }

    @Test
    void getOfferEditTest() {
        String url = "/edge/rest/v2/offers/925183846730025/offer-edits/925184068850125";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getOfferEditSuccessfulResponse.json")));

        OfferEditGetRequest offerEditGetRequest = new OfferEditGetRequest.Builder(925184068850125L, 925183846730025L).build();
        ResponseStreamObserver<OfferEdit> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyOfferEdit);
        clientRest.getOfferEdit(offerEditGetRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void getOfferEditsTest() {
        String url = "/edge/rest/v2/offers/925183846730025/offer-edits";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getOfferEditsSuccessfulResponse.json")));

        OfferEditsGetRequest offerEditsGetRequest = new OfferEditsGetRequest.Builder(925183846730025L).build();
        ResponseStreamObserver<OfferEdit> streamObserver = new SuccessfulResponseStreamObserver<>(2, this::verifyOfferEdit);
        clientRest.getOfferEdits(offerEditsGetRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyOfferEdit(OfferEdit offerEdit) {
        assertThat(offerEdit).isNotNull();
        assertThat(offerEdit.getId()).isNotNull();
        assertThat(offerEdit.getOfferId()).isNotNull();
        assertThat(offerEdit.getStatus()).isNotNull();
        assertThat(offerEdit.getOddsType()).isNotNull();
        assertThat(offerEdit.getOddsBefore()).isNotNull();
        assertThat(offerEdit.getOddsAfter()).isNotNull();
        assertThat(offerEdit.getStakeBefore()).isNotNull();
        assertThat(offerEdit.getStakeAfter()).isNotNull();
    }

    @Test
    void getAggregatedMatchedBetsTest() {
        String url = "/edge/rest/bets/matched/aggregated";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getAggregatedMatchedBetsResponse.json")));

        AggregatedMatchedBetsRequest aggregatedMatchedBetsRequest = new AggregatedMatchedBetsRequest.Builder().build();
        ResponseStreamObserver<AggregatedMatchedBet> streamObserver = new SuccessfulResponseStreamObserver<>(2, this::verifyAggregatedMatchedBet);
        clientRest.getAggregatedMatchedBets(aggregatedMatchedBetsRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyAggregatedMatchedBet(AggregatedMatchedBet aggregatedMatchedBet) {
        assertThat(aggregatedMatchedBet).isNotNull();
        assertThat(aggregatedMatchedBet.getEventId()).isNotNull();
        assertThat(aggregatedMatchedBet.getMarketId()).isNotNull();
        assertThat(aggregatedMatchedBet.getRunnerId()).isNotNull();
        assertThat(aggregatedMatchedBet.getSide()).isNotNull();
        assertThat(aggregatedMatchedBet.getOddsType()).isNotNull();
        assertThat(aggregatedMatchedBet.getOdds()).isNotNull();
        assertThat(aggregatedMatchedBet.getStake()).isNotNull();
    }

    @Test
    void getCancelledMatchedBetsTest() {
        String url = "/edge/rest/bets";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withQueryParam("status", new EqualToPattern("CANCELLED"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getCancelledMatchedBetsResponse.json")));

        CancelledMatchedBetsRequest cancelledMatchedBetsRequest = new CancelledMatchedBetsRequest.Builder().build();
        ResponseStreamObserver<MatchedBet> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyCancelledMatchedBet);
        clientRest.getCancelledMatchedBets(cancelledMatchedBetsRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyCancelledMatchedBet(MatchedBet matchedBet) {
        assertThat(matchedBet).isNotNull();
        assertThat(matchedBet.getOfferId()).isNotNull();
        assertThat(matchedBet.getCurrency()).isNotNull();
        assertThat(matchedBet.getOddsType()).isNotNull();
        assertThat(matchedBet.getOdds()).isNotNull();
        assertThat(matchedBet.getStake()).isNotNull();
        assertThat(matchedBet.getCommission()).isNotNull();
        assertThat(matchedBet.getStatus()).isEqualTo(MatchedBetStatus.CANCELLED);
    }

    @Test
    void getPositionsTest() {
        String url = "/edge/rest/account/positions";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/offers/getPositionsResponse.json")));

        PositionsRequest positionsRequest = new PositionsRequest.Builder().build();
        ResponseStreamObserver<Position> streamObserver = new SuccessfulResponseStreamObserver<>(3, this::verifyPosition);
        clientRest.getPositions(positionsRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyPosition(Position position) {
        assertThat(position).isNotNull();
        assertThat(position.getEventId()).isNotNull();
        assertThat(position.getMarketId()).isNotNull();
        assertThat(position.getRunnerId()).isNotNull();
        assertThat(Arrays.asList(position.getPotentialProfit(), position.getPotentialLoss()))
                .containsOnlyOnce((BigDecimal) null);
    }

}
