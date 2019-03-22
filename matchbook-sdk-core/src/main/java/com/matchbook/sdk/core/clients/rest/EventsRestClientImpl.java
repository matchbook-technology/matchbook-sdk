package com.matchbook.sdk.core.clients.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.events.Event;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventsResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.Market;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketsResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.Runner;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnerRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnerResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnersRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnersResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.Sport;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsResponse;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.squareup.okhttp.Request;

public class EventsRestClientImpl extends AbstractRestClient implements EventsRestClient {

    private final ClientConnectionManager clientConnectionManager;
    private final ObjectReader sportsResponseReader;
    private final ObjectReader eventResponseReader;
    private final ObjectReader eventsResponseReader;
    private final ObjectReader marketResponseReader;
    private final ObjectReader marketsResponseReader;
    private final ObjectReader runnerResponseReader;
    private final ObjectReader runnersResponseReader;

    public EventsRestClientImpl(ClientConnectionManager clientConnectionManager) {
        super(clientConnectionManager.getMapper());

        this.clientConnectionManager = clientConnectionManager;

        ObjectMapper objectMapper = clientConnectionManager.getMapper();
        this.sportsResponseReader = objectMapper.readerFor(SportsResponse.class);
        this.eventResponseReader = objectMapper.readerFor(EventResponse.class);
        this.eventsResponseReader = objectMapper.readerFor(EventsResponse.class);
        this.marketResponseReader = objectMapper.readerFor(MarketResponse.class);
        this.marketsResponseReader = objectMapper.readerFor(MarketsResponse.class);
        this.runnerResponseReader = objectMapper.readerFor(RunnerResponse.class);
        this.runnersResponseReader = objectMapper.readerFor(RunnersResponse.class);
    }

    @Override
    public void getSports(SportsRequest sportsRequest, StreamObserver<Sport> sportsObserver) {
        Request request = getRequest(sportsRequest.resourcePath(), sportsRequest.parameters());
        clientConnectionManager.getHttpClient()
                .newCall(request)
                .enqueue(new RestCallback<>(sportsObserver, sportsResponseReader));
    }

    @Override
    public void getEvent(EventRequest eventRequest, StreamObserver<Event> eventObserver) {
        Request request = getRequest(eventRequest.resourcePath(), eventRequest.parameters());
        clientConnectionManager.getHttpClient()
                .newCall(request)
                .enqueue(new RestCallback<>(eventObserver, eventResponseReader));
    }

    @Override
    public void getEvents(EventsRequest eventsRequest, StreamObserver<Event> eventsObserver) {
        Request request = getRequest(eventsRequest.resourcePath(), eventsRequest.parameters());
        clientConnectionManager.getHttpClient()
                .newCall(request)
                .enqueue(new RestCallback<>(eventsObserver, eventsResponseReader));
    }

    @Override
    public void getMarket(MarketRequest marketRequest, StreamObserver<Market> marketObserver) {
        Request request = getRequest(marketRequest.resourcePath(), marketRequest.parameters());
        clientConnectionManager.getHttpClient()
                .newCall(request)
                .enqueue(new RestCallback<>(marketObserver, marketResponseReader));
    }

    @Override
    public void getMarkets(MarketsRequest marketsRequest, StreamObserver<Market> marketsObserver) {
        Request request = getRequest(marketsRequest.resourcePath(), marketsRequest.parameters());
        clientConnectionManager.getHttpClient()
                .newCall(request)
                .enqueue(new RestCallback<>(marketsObserver, marketsResponseReader));
    }

    @Override
    public void getRunner(RunnerRequest runnerRequest, StreamObserver<Runner> runnerObserver) {
        Request request = getRequest(runnerRequest.resourcePath(), runnerRequest.parameters());
        clientConnectionManager.getHttpClient()
                .newCall(request)
                .enqueue(new RestCallback<>(runnerObserver, runnerResponseReader));
    }

    @Override
    public void getRunners(RunnersRequest runnersRequest, StreamObserver<Runner> runnersObserver) {
        Request request = getRequest(runnersRequest.resourcePath(), runnersRequest.parameters());
        clientConnectionManager.getHttpClient()
                .newCall(request)
                .enqueue(new RestCallback<>(runnersObserver, runnersResponseReader));
    }

}
