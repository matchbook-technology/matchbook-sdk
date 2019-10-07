package com.matchbook.sdk.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.events.Event;
import com.matchbook.sdk.rest.dtos.events.EventRequest;
import com.matchbook.sdk.rest.dtos.events.EventsRequest;
import com.matchbook.sdk.rest.dtos.events.Market;
import com.matchbook.sdk.rest.dtos.events.MarketRequest;
import com.matchbook.sdk.rest.dtos.events.MarketsRequest;
import com.matchbook.sdk.rest.dtos.events.Runner;
import com.matchbook.sdk.rest.dtos.events.RunnerRequest;
import com.matchbook.sdk.rest.dtos.events.RunnersRequest;
import com.matchbook.sdk.rest.dtos.events.Sport;
import com.matchbook.sdk.rest.dtos.events.SportsRequest;

import java.util.Objects;

import org.junit.jupiter.api.Test;

public class EventsClientRest_IT extends MatchbookSDKClientRest_IT<EventsClientRest> {

    @Override
    protected EventsClientRest newClientRest(ConnectionManager connectionManager) {
        return new EventsClientRest(connectionManager);
    }

    @Test
    void getSportsTest() {
        String url = "/edge/rest/lookups/sports";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getSportsSuccessfulResponse.json")));

        SportsRequest sportsRequest = new SportsRequest.Builder().build();
        ResponseStreamObserver<Sport> streamObserver = new SuccessfulResponseStreamObserver<>(5, this::verifySport);
        clientRest.getSports(sportsRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifySport(Sport sport) {
        assertThat(sport).isNotNull();
        assertThat(sport.getId()).isNotNull();
        assertThat(sport.getName()).isNotEmpty();
    }

    @Test
    void getEventTest() {
        String url = "/edge/rest/events/395729780570010";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getEventSuccessfulResponse.json")));

        EventRequest eventRequest = new EventRequest.Builder(395729780570010L).build();
        ResponseStreamObserver<Event> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyEvent);
        clientRest.getEvent(eventRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void getEventsTest() {
        String url = "/edge/rest/events";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getEventsSuccessfulResponse.json")));

        EventsRequest eventsRequest = new EventsRequest.Builder().build();
        ResponseStreamObserver<Event> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyEvent);
        clientRest.getEvents(eventsRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyEvent(Event event) {
        assertThat(event).isNotNull();
        assertThat(event.getId()).isNotNull();
        assertThat(event.getSportId()).isNotNull();
        assertThat(event.getCategoryIds()).isNotEmpty();
        assertThat(event.getName()).isNotEmpty();
        assertThat(event.getStatus()).isNotNull();
        if (Objects.nonNull(event.getMarkets())) {
            event.getMarkets().forEach(market -> {
                assertThat(market).isNotNull();
                assertThat(market.getId()).isNotNull();
            });
        }
        if (Objects.nonNull(event.getEventParticipants())) {
            event.getEventParticipants().forEach(eventParticipant -> {
                assertThat(eventParticipant).isNotNull();
                assertThat(eventParticipant.getId()).isNotNull();
            });
        }
        if (Objects.nonNull(event.getMetaTags())) {
            event.getMetaTags().forEach(metaTag -> {
                assertThat(metaTag).isNotNull();
                assertThat(metaTag.getId()).isNotNull();
                assertThat(metaTag.getName()).isNotEmpty();
                assertThat(metaTag.getType()).isNotNull();
            });
        }
    }

    @Test
    void getMarketTest() {
        String url = "/edge/rest/events/395729780570010/markets/395729860260010";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getMarketSuccessfulResponse.json")));

        MarketRequest marketRequest = new MarketRequest
                .Builder(395729860260010L, 395729780570010L)
                .build();
        ResponseStreamObserver<Market> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyMarket);
        clientRest.getMarket(marketRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void getMarketWithUnknownMarketTypeTest() {
        String url = "/edge/rest/events/395729780570010/markets/395729860260010";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getMarketOfUnknownTypeSuccessfulResponse.json")));

        MarketRequest marketRequest = new MarketRequest
                .Builder(395729860260010L, 395729780570010L)
                .build();
        ResponseStreamObserver<Market> streamObserver = new SuccessfulResponseStreamObserver<>(1, market -> {
            verifyMarket(market);
            assertThat(market.getMarketType()).isEqualTo(MarketType.UNKNOWN);
        });
        clientRest.getMarket(marketRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void getMarketsTest() {
        String url = "/edge/rest/events/395729780570010/markets";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getMarketsSuccessfulResponse.json")));

        MarketsRequest marketsRequest = new MarketsRequest.Builder(395729780570010L).build();
        ResponseStreamObserver<Market> streamObserver = new SuccessfulResponseStreamObserver<>(8, this::verifyMarket);
        clientRest.getMarkets(marketsRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyMarket(Market market) {
        assertThat(market).isNotNull();
        assertThat(market.getId()).isNotNull();
        assertThat(market.getEventId()).isNotNull();
        assertThat(market.getStatus()).isNotNull();
        assertThat(market.getMarketType()).isNotNull();
        if (Objects.nonNull(market.getRunners())) {
            market.getRunners().forEach(runner -> {
                assertThat(runner);
                assertThat(runner.getId()).isNotNull();
            });
        }
    }

    @Test
    void getRunnerTest() {
        String url = "/edge/rest/events/395729780570010/markets/395729860260010/runners/395729860800010";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getRunnerSuccessfulResponse.json")));

        RunnerRequest runnerRequest = new RunnerRequest
                .Builder(395729780570010L, 395729860260010L, 395729860800010L)
                .build();
        ResponseStreamObserver<Runner> streamObserver = new SuccessfulResponseStreamObserver<>(1, this::verifyRunner);
        clientRest.getRunner(runnerRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    void getRunnersTest() {
        String url = "/edge/rest/events/395729780570010/markets/395729860260010/runners";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getRunnersSuccessfulResponse.json")));

        RunnersRequest runnersRequest = new RunnersRequest
                .Builder(395729780570010L, 395729860260010L)
                .build();
        ResponseStreamObserver<Runner> streamObserver = new SuccessfulResponseStreamObserver<>(3, this::verifyRunner);
        clientRest.getRunners(runnersRequest, streamObserver);
        streamObserver.waitTermination();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyRunner(Runner runner) {
        assertThat(runner).isNotNull();
        assertThat(runner.getId()).isNotNull();
        assertThat(runner.getEventId()).isNotNull();
        assertThat(runner.getMarketId()).isNotNull();
        assertThat(runner.getStatus()).isNotNull();
        if (Objects.nonNull(runner.getPrices())) {
            runner.getPrices().forEach(price -> {
                assertThat(price).isNotNull();
                assertThat(price.getOddsType()).isNotNull();
                assertThat(price.getOdds()).isNotNull();
            });
        }
    }

}
