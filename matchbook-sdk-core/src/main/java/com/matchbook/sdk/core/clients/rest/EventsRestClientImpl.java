package com.matchbook.sdk.core.clients.rest;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.Event;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventResponse;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.Market;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.Runner;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnerRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnersRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.Sport;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsResponse;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHTTPException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

public class EventsRestClientImpl extends AbstractRestClient implements EventsRestClient {

    private final ClientConnectionManager clientConnectionManager;
    private final ObjectWriter sportsRequestWriter;
    private final ObjectWriter eventRequestWriter;
    private final ObjectReader sportsResponseReader;
    private final ObjectReader eventResponseReader;

    public EventsRestClientImpl(ClientConnectionManager clientConnectionManager) {
        super(clientConnectionManager.getMapper());

        this.clientConnectionManager = clientConnectionManager;

        ObjectMapper objectMapper = clientConnectionManager.getMapper();
        this.sportsRequestWriter = objectMapper.writerFor(SportsRequest.class);
        this.eventRequestWriter = objectMapper.writerFor(EventRequest.class);
        this.sportsResponseReader = objectMapper.readerFor(SportsResponse.class);
        this.eventResponseReader = objectMapper.readerFor(EventResponse.class);
    }

    @Override
    public void getSports(SportsRequest sportsRequest, StreamObserver<Sport> sportsObserver) {
        try {
            String sportsUrl = clientConnectionManager.getClientConfig().buildUrl("lookups/sports");
            String requestBody = sportsRequestWriter.writeValueAsString(sportsRequest);
            Request request = buildRequest(sportsUrl, requestBody);

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
            String sportsUrl = clientConnectionManager.getClientConfig().buildUrl(path);
            String requestBody = eventRequestWriter.writeValueAsString(eventRequest);
            Request request = buildRequest(sportsUrl, requestBody);

            clientConnectionManager.getHttpClient()
                    .newCall(request)
                    .enqueue(new RestCallback<>(eventObserver, eventResponseReader));
        } catch (Exception e) {
            eventObserver.onError(new MatchbookSDKHTTPException(e.getCause()));
        }
    }

    @Override
    public void getEvents(EventsRequest eventsRequest, StreamObserver<Event> eventsObserver) {
        // do nothing
    }

    @Override
    public void getMarket(MarketRequest marketRequest, StreamObserver<Market> marketObserver) {
        // do nothing
    }

    @Override
    public void getMarkets(MarketsRequest marketsRequest, StreamObserver<Market> marketsObserver) {
        // do nothing
    }

    @Override
    public void getRunner(RunnerRequest runnerRequest, StreamObserver<Runner> runnerObserver) {
        // do nothing
    }

    @Override
    public void getRunners(RunnersRequest runnersRequest, StreamObserver<Runner> runnersObserver) {
        // do nothing
    }

    private class RestCallback<T> implements Callback {

        private final StreamObserver<T> observer;
        private final ObjectReader objectReader;

        private RestCallback(StreamObserver<T> observer, ObjectReader objectReader) {
            this.observer = observer;
            this.objectReader = objectReader;
        }

        @Override
        public void onResponse(Response response) throws IOException {
            try (ResponseBody responseBody = response.body()) {
                if (!response.isSuccessful()) {
                    errorHandler(response, observer);
                    return;
                }
                RestResponse<T> restResponse = objectReader.readValue(responseBody.byteStream());
                restResponse.getContent().forEach(observer::onNext);
                observer.onCompleted();
            }
        }

        @Override
        public void onFailure(Request request, IOException e) {
            MatchbookSDKHTTPException matchbookException = new MatchbookSDKHTTPException(e.getMessage(), e);
            observer.onError(matchbookException);
        }

    }

}
