package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
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
import com.matchbook.sdk.rest.readers.events.EventReader;
import com.matchbook.sdk.rest.readers.events.EventsReader;
import com.matchbook.sdk.rest.readers.events.MarketReader;
import com.matchbook.sdk.rest.readers.events.MarketsReader;
import com.matchbook.sdk.rest.readers.events.RunnerReader;
import com.matchbook.sdk.rest.readers.events.RunnersReader;
import com.matchbook.sdk.rest.readers.events.SportsReader;

public class EventsClientRest extends AbstractClientRest implements EventsClient {

    public EventsClientRest(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void getSports(SportsRequest sportsRequest, StreamObserver<Sport> sportsObserver) {
        String url = buildSportsUrl(sportsRequest.resourcePath());
        getRequest(url, sportsRequest, sportsObserver, new SportsReader());
    }

    @Override
    public void getEvent(EventRequest eventRequest, StreamObserver<Event> eventObserver) {
        String url = buildSportsUrl(eventRequest.resourcePath());
        getRequest(url, eventRequest, eventObserver, new EventReader());
    }

    @Override
    public void getEvents(EventsRequest eventsRequest, StreamObserver<Event> eventsObserver) {
        String url = buildSportsUrl(eventsRequest.resourcePath());
        getRequest(url, eventsRequest, eventsObserver, new EventsReader());
    }

    @Override
    public void getMarket(MarketRequest marketRequest, StreamObserver<Market> marketObserver) {
        String url = buildSportsUrl(marketRequest.resourcePath());
        getRequest(url, marketRequest, marketObserver, new MarketReader());
    }

    @Override
    public void getMarkets(MarketsRequest marketsRequest, StreamObserver<Market> marketsObserver) {
        String url = buildSportsUrl(marketsRequest.resourcePath());
        getRequest(url, marketsRequest, marketsObserver, new MarketsReader());
    }

    @Override
    public void getRunner(RunnerRequest runnerRequest, StreamObserver<Runner> runnerObserver) {
        String url = buildSportsUrl(runnerRequest.resourcePath());
        getRequest(url, runnerRequest, runnerObserver, new RunnerReader());
    }

    @Override
    public void getRunners(RunnersRequest runnersRequest, StreamObserver<Runner> runnersObserver) {
        String url = buildSportsUrl(runnersRequest.resourcePath());
        getRequest(url, runnersRequest, runnersObserver, new RunnersReader());
    }

}
