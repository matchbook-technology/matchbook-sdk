package com.matchbook.sdk.core.clients.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.events.Event;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventsResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.Market;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketsResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.Runner;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnerRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnersRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnersResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.Sport;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsResponse;
import com.matchbook.sdk.core.configs.ClientConnectionManager;

public class EventsRestClientImpl extends AbstractRestClient implements EventsRestClient {

    private final ObjectReader sportsResponseReader;
    private final ObjectReader eventResponseReader;
    private final ObjectReader eventsResponseReader;
    private final ObjectReader marketResponseReader;
    private final ObjectReader marketsResponseReader;
    private final ObjectReader runnerResponseReader;
    private final ObjectReader runnersResponseReader;

    public EventsRestClientImpl(ClientConnectionManager clientConnectionManager) {
        super(clientConnectionManager);

        ObjectMapper objectMapper = clientConnectionManager.getMapper();
        this.sportsResponseReader = objectMapper.readerFor(SportsResponse.class);
        this.eventResponseReader = objectMapper.readerFor(Event.class);
        this.eventsResponseReader = objectMapper.readerFor(EventsResponse.class);
        this.marketResponseReader = objectMapper.readerFor(Market.class);
        this.marketsResponseReader = objectMapper.readerFor(MarketsResponse.class);
        this.runnerResponseReader = objectMapper.readerFor(Runner.class);
        this.runnersResponseReader = objectMapper.readerFor(RunnersResponse.class);
    }

    @Override
    public void getSports(SportsRequest sportsRequest, StreamObserver<Sport> sportsObserver) {
        String url = clientConnectionManager.getClientConfig().buildUrl(sportsRequest.resourcePath());
        getRequest(url, sportsRequest.parameters(), sportsObserver, sportsResponseReader);
    }

    @Override
    public void getEvent(EventRequest eventRequest, StreamObserver<Event> eventObserver) {
        String url = clientConnectionManager.getClientConfig().buildUrl(eventRequest.resourcePath());
        getRequest(url, eventRequest.parameters(), eventObserver, eventResponseReader);
    }

    @Override
    public void getEvents(EventsRequest eventsRequest, StreamObserver<Event> eventsObserver) {
        String url = clientConnectionManager.getClientConfig().buildUrl(eventsRequest.resourcePath());
        getRequest(url, eventsRequest.parameters(), eventsObserver, eventsResponseReader);
    }

    @Override
    public void getMarket(MarketRequest marketRequest, StreamObserver<Market> marketObserver) {
        String url = clientConnectionManager.getClientConfig().buildUrl(marketRequest.resourcePath());
        getRequest(url, marketRequest.parameters(), marketObserver, marketResponseReader);
    }

    @Override
    public void getMarkets(MarketsRequest marketsRequest, StreamObserver<Market> marketsObserver) {
        String url = clientConnectionManager.getClientConfig().buildUrl(marketsRequest.resourcePath());
        getRequest(url, marketsRequest.parameters(), marketsObserver, marketsResponseReader);
    }

    @Override
    public void getRunner(RunnerRequest runnerRequest, StreamObserver<Runner> runnerObserver) {
        String url = clientConnectionManager.getClientConfig().buildUrl(runnerRequest.resourcePath());
        getRequest(url, runnerRequest.parameters(), runnerObserver, runnerResponseReader);
    }

    @Override
    public void getRunners(RunnersRequest runnersRequest, StreamObserver<Runner> runnersObserver) {
        String url = clientConnectionManager.getClientConfig().buildUrl(runnersRequest.resourcePath());
        getRequest(url, runnersRequest.parameters(), runnersObserver, runnersResponseReader);
    }

}
