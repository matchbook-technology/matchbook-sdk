package com.matchbook.sdk.core.clients.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import com.matchbook.sdk.core.exceptions.MatchbookSDKHTTPException;
import com.squareup.okhttp.Request;

public class EventsRestClientImpl extends AbstractRestClient implements EventsRestClient {

    private final ClientConnectionManager clientConnectionManager;
    private final ObjectWriter sportsRequestWriter;
    private final ObjectWriter eventRequestWriter;
    private final ObjectWriter eventsRequestWriter;
    private final ObjectWriter marketRequestWriter;
    private final ObjectWriter marketsRequestWriter;
    private final ObjectWriter runnerRequestWriter;
    private final ObjectWriter runnersRequestWriter;
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
        this.sportsRequestWriter = objectMapper.writerFor(SportsRequest.class);
        this.eventRequestWriter = objectMapper.writerFor(EventRequest.class);
        this.eventsRequestWriter = objectMapper.writerFor(EventsRequest.class);
        this.marketRequestWriter = objectMapper.writerFor(MarketRequest.class);
        this.marketsRequestWriter = objectMapper.writerFor(MarketsRequest.class);
        this.runnerRequestWriter = objectMapper.writerFor(RunnerRequest.class);
        this.runnersRequestWriter = objectMapper.writerFor(RunnersRequest.class);

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
        try {
            String sportsUrl = clientConnectionManager.getClientConfig().buildUrl("lookups/sports");
            String requestBody = sportsRequestWriter.writeValueAsString(sportsRequest);
            Request request = postRequest(sportsUrl, requestBody);

            clientConnectionManager.getHttpClient()
                    .newCall(request)
                    .enqueue(new RestCallback<>(sportsObserver, sportsResponseReader));
        } catch (Exception e) {
            sportsObserver.onError(new MatchbookSDKHTTPException(e.getCause()));
        }
    }

    @Override
    public void getEvent(EventRequest eventRequest, StreamObserver<Event> eventObserver) {
        try {
            String path = "events/" + eventRequest.getEventId();
            String eventUrl = clientConnectionManager.getClientConfig().buildUrl(path);
            String requestBody = eventRequestWriter.writeValueAsString(eventRequest);
            Request request = postRequest(eventUrl, requestBody);

            clientConnectionManager.getHttpClient()
                    .newCall(request)
                    .enqueue(new RestCallback<>(eventObserver, eventResponseReader));
        } catch (Exception e) {
            eventObserver.onError(new MatchbookSDKHTTPException(e.getCause()));
        }
    }

    @Override
    public void getEvents(EventsRequest eventsRequest, StreamObserver<Event> eventsObserver) {
        try {
            String path = "events";
            String eventsUrl = clientConnectionManager.getClientConfig().buildUrl(path);
            String requestBody = eventsRequestWriter.writeValueAsString(eventsRequest);
            Request request = postRequest(eventsUrl, requestBody);

            clientConnectionManager.getHttpClient()
                    .newCall(request)
                    .enqueue(new RestCallback<>(eventsObserver, eventsResponseReader));
        } catch (Exception e) {
            eventsObserver.onError(new MatchbookSDKHTTPException(e.getCause()));
        }
    }

    @Override
    public void getMarket(MarketRequest marketRequest, StreamObserver<Market> marketObserver) {
        try {
            String path = "events/" + marketRequest.getEventId() + "/markets/" + marketRequest.getMarketId();
            String marketUrl = clientConnectionManager.getClientConfig().buildUrl(path);
            String requestBody = marketRequestWriter.writeValueAsString(marketRequest);
            Request request = postRequest(marketUrl, requestBody);

            clientConnectionManager.getHttpClient()
                    .newCall(request)
                    .enqueue(new RestCallback<>(marketObserver, marketResponseReader));
        } catch (Exception e) {
            marketObserver.onError(new MatchbookSDKHTTPException(e.getCause()));
        }
    }

    @Override
    public void getMarkets(MarketsRequest marketsRequest, StreamObserver<Market> marketsObserver) {
        try {
            String path = "events/" + marketsRequest.getEventId() + "/markets";
            String marketsUrl = clientConnectionManager.getClientConfig().buildUrl(path);
            String requestBody = marketsRequestWriter.writeValueAsString(marketsRequest);
            Request request = postRequest(marketsUrl, requestBody);

            clientConnectionManager.getHttpClient()
                    .newCall(request)
                    .enqueue(new RestCallback<>(marketsObserver, marketsResponseReader));
        } catch (Exception e) {
            marketsObserver.onError(new MatchbookSDKHTTPException(e.getCause()));
        }
    }

    @Override
    public void getRunner(RunnerRequest runnerRequest, StreamObserver<Runner> runnerObserver) {
        try {
            String path = "events/" + runnerRequest.getEventId() + "/markets/" + runnerRequest.getMarketId()
                    + "/runners/" + runnerRequest.getRunnerId();
            String runnerUrl = clientConnectionManager.getClientConfig().buildUrl(path);
            String requestBody = runnerRequestWriter.writeValueAsString(runnerRequest);
            Request request = postRequest(runnerUrl, requestBody);

            clientConnectionManager.getHttpClient()
                    .newCall(request)
                    .enqueue(new RestCallback<>(runnerObserver, runnerResponseReader));
        } catch (Exception e) {
            runnerObserver.onError(new MatchbookSDKHTTPException(e.getCause()));
        }
    }

    @Override
    public void getRunners(RunnersRequest runnersRequest, StreamObserver<Runner> runnersObserver) {
        try {
            String path = "events/" + runnersRequest.getEventId() + "/markets/" + runnersRequest.getMarketId()
                    + "/runners/";
            String runnersUrl = clientConnectionManager.getClientConfig().buildUrl(path);
            String requestBody = runnersRequestWriter.writeValueAsString(runnersRequest);
            Request request = postRequest(runnersUrl, requestBody);

            clientConnectionManager.getHttpClient()
                    .newCall(request)
                    .enqueue(new RestCallback<>(runnersObserver, runnersResponseReader));
        } catch (Exception e) {
            runnersObserver.onError(new MatchbookSDKHTTPException(e.getCause()));
        }
    }

}
