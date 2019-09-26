package com.matchbook.sdk.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.ConnectionManager;
import com.matchbook.sdk.rest.EventsClientRest;
import com.matchbook.sdk.rest.MatchbookSDKClientRest_IT;
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
import org.junit.Test;

public class EventsClientRest_IT extends MatchbookSDKClientRest_IT<EventsClientRest> {

    @Override
    protected EventsClientRest newClientRest(ConnectionManager connectionManager) {
        return new EventsClientRest(connectionManager);
    }

    @Test
    public void getSportsTest() throws InterruptedException {
        String url = "/edge/rest/lookups/sports";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getSportsSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(6);

        SportsRequest sportsRequest = new SportsRequest.Builder().build();

        clientRest.getSports(sportsRequest, new StreamObserver<Sport>() {

            @Override
            public void onNext(Sport sport) {
                assertNotNull(sport);
                assertNotNull(sport.getId());
                assertThat(sport.getName()).isNotEmpty();
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

        boolean await = countDownLatch.await(10, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void getEventTest() throws InterruptedException {
        String url = "/edge/rest/events/395729780570010";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getEventSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        EventRequest eventRequest = new EventRequest.Builder(395729780570010L).build();

        clientRest.getEvent(eventRequest, new StreamObserver<Event>() {

            @Override
            public void onNext(Event event) {
                verifyEvent(event);
                countDownLatch.countDown();
            }

            @Override
            public void onError(MatchbookSDKException e) {
                fail(e.getMessage());
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });

        boolean await = countDownLatch.await(10, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void getEventsTest() throws InterruptedException {
        String url = "/edge/rest/events";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getEventsSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        EventsRequest eventsRequest = new EventsRequest.Builder().build();

        clientRest.getEvents(eventsRequest, new StreamObserver<Event>() {

            @Override
            public void onNext(Event event) {
                verifyEvent(event);
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

        boolean await = countDownLatch.await(10, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyEvent(Event event) {
        assertNotNull(event);
        assertNotNull(event.getId());
        assertNotNull(event.getSportId());
        assertThat(event.getCategoryIds()).isNotEmpty();
        assertThat(event.getName()).isNotEmpty();
        assertNotNull(event.getStatus());
        if (Objects.nonNull(event.getMarkets())) {
            event.getMarkets().forEach(market -> {
                assertNotNull(market);
                assertNotNull(market.getId());
            });
        }
        if (Objects.nonNull(event.getEventParticipants())) {
            event.getEventParticipants().forEach(eventParticipant -> {
                assertNotNull(eventParticipant);
                assertNotNull(eventParticipant.getId());
            });
        }
        if (Objects.nonNull(event.getMetaTags())) {
            event.getMetaTags().forEach(metaTag -> {
                assertNotNull(metaTag);
                assertNotNull(metaTag.getId());
                assertThat(metaTag.getName()).isNotEmpty();
                assertNotNull(metaTag.getType());
            });
        }
    }

    @Test
    public void getMarketTest() throws InterruptedException {
        String url = "/edge/rest/events/395729780570010/markets/395729860260010";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getMarketSuccessfulResponse.json")));


        final CountDownLatch countDownLatch = new CountDownLatch(2);
        MarketRequest marketRequest = new MarketRequest
                .Builder(395729860260010L, 395729780570010L)
                .build();

        clientRest.getMarket(marketRequest, new StreamObserver<Market>() {

            @Override
            public void onNext(Market market) {
                verifyMarket(market);
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

        boolean await = countDownLatch.await(10, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void getMarketsTest() throws InterruptedException {
        String url = "/edge/rest/events/395729780570010/markets";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getMarketsSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(9);

        MarketsRequest marketsRequest = new MarketsRequest.Builder(395729780570010L).build();

        clientRest.getMarkets(marketsRequest, new StreamObserver<Market>() {

            @Override
            public void onNext(Market market) {
                verifyMarket(market);
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

        boolean await = countDownLatch.await(10, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyMarket(Market market) {
        assertNotNull(market);
        assertNotNull(market.getId());
        assertNotNull(market.getEventId());
        assertNotNull(market.getStatus());
        assertNotNull(market.getMarketType());
        if (Objects.nonNull(market.getRunners())) {
            market.getRunners().forEach(runner -> {
                assertNotNull(runner);
                assertNotNull(runner.getId());
            });
        }
    }

    @Test
    public void getRunnerTest() throws InterruptedException {
        String url = "/edge/rest/events/395729780570010/markets/395729860260010/runners/395729860800010";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getRunnerSuccessfulResponse.json")));


        final CountDownLatch countDownLatch = new CountDownLatch(2);

        RunnerRequest runnerRequest = new RunnerRequest
                .Builder(395729780570010L, 395729860260010L, 395729860800010L)
                .build();

        clientRest.getRunner(runnerRequest, new StreamObserver<Runner>() {

            @Override
            public void onNext(Runner runner) {
                verifyRunner(runner);
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

        boolean await = countDownLatch.await(10, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    @Test
    public void getRunnersTest() throws InterruptedException {
        String url = "/edge/rest/events/395729780570010/markets/395729860260010/runners";
        wireMockServer.stubFor(get(urlPathEqualTo(url))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("matchbook/events/getRunnersSuccessfulResponse.json")));

        final CountDownLatch countDownLatch = new CountDownLatch(4);

        RunnersRequest runnersRequest = new RunnersRequest
                .Builder(395729780570010L, 395729860260010L)
                .build();

        clientRest.getRunners(runnersRequest, new StreamObserver<Runner>() {

            @Override
            public void onNext(Runner runner) {
                verifyRunner(runner);
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

        boolean await = countDownLatch.await(10, TimeUnit.SECONDS);
        assertThat(await).isTrue();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(url))
                .withCookie("mb-client-type", equalTo("mb-sdk")));
    }

    private void verifyRunner(Runner runner) {
        assertNotNull(runner);
        assertNotNull(runner.getId());
        assertNotNull(runner.getEventId());
        assertNotNull(runner.getMarketId());
        assertNotNull(runner.getStatus());
        if (Objects.nonNull(runner.getPrices())) {
            runner.getPrices().forEach(price -> {
                assertNotNull(price);
                assertNotNull(price.getOddsType());
                assertNotNull(price.getOdds());
            });
        }
    }

}
