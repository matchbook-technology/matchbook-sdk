package com.matchbook.sdk.core;

import com.matchbook.sdk.common.StreamObserver;
import com.matchbook.sdk.core.dtos.events.Event;
import com.matchbook.sdk.core.dtos.events.EventRequest;
import com.matchbook.sdk.core.dtos.events.EventsRequest;
import com.matchbook.sdk.core.dtos.events.EventsResponse;
import com.matchbook.sdk.core.dtos.events.Market;
import com.matchbook.sdk.core.dtos.events.MarketRequest;
import com.matchbook.sdk.core.dtos.events.MarketsRequest;
import com.matchbook.sdk.core.dtos.events.MarketsResponse;
import com.matchbook.sdk.core.dtos.events.Runner;
import com.matchbook.sdk.core.dtos.events.RunnerRequest;
import com.matchbook.sdk.core.dtos.events.RunnersRequest;
import com.matchbook.sdk.core.dtos.events.RunnersResponse;
import com.matchbook.sdk.core.dtos.events.Sport;
import com.matchbook.sdk.core.dtos.events.SportsRequest;
import com.matchbook.sdk.core.dtos.events.SportsResponse;

public class EventsClientRest extends AbstractRestClient implements EventsClient {

    public EventsClientRest(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void getSports(SportsRequest sportsRequest, StreamObserver<Sport> sportsObserver) {
        String url = buildSportsUrl(sportsRequest.resourcePath());
        getRequest(url, sportsRequest, sportsObserver, SportsResponse.class);
    }

    @Override
    public void getEvent(EventRequest eventRequest, StreamObserver<Event> eventObserver) {
        String url = buildSportsUrl(eventRequest.resourcePath());
        getRequest(url, eventRequest, eventObserver, Event.class);
    }

    @Override
    public void getEvents(EventsRequest eventsRequest, StreamObserver<Event> eventsObserver) {
        String url = buildSportsUrl(eventsRequest.resourcePath());
        getRequest(url, eventsRequest, eventsObserver, EventsResponse.class);
    }

    @Override
    public void getMarket(MarketRequest marketRequest, StreamObserver<Market> marketObserver) {
        String url = buildSportsUrl(marketRequest.resourcePath());
        getRequest(url, marketRequest, marketObserver, Market.class);
    }

    @Override
    public void getMarkets(MarketsRequest marketsRequest, StreamObserver<Market> marketsObserver) {
        String url = buildSportsUrl(marketsRequest.resourcePath());
        getRequest(url, marketsRequest, marketsObserver, MarketsResponse.class);
    }

    @Override
    public void getRunner(RunnerRequest runnerRequest, StreamObserver<Runner> runnerObserver) {
        String url = buildSportsUrl(runnerRequest.resourcePath());
        getRequest(url, runnerRequest, runnerObserver, Runner.class);
    }

    @Override
    public void getRunners(RunnersRequest runnersRequest, StreamObserver<Runner> runnersObserver) {
        String url = buildSportsUrl(runnersRequest.resourcePath());
        getRequest(url, runnersRequest, runnersObserver, RunnersResponse.class);
    }

}
