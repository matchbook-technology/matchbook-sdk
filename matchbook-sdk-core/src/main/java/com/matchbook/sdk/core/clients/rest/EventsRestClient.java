package com.matchbook.sdk.core.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.events.Event;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.EventsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.Market;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.MarketsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.Runner;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnerRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.RunnersRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.Sport;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsRequest;

public interface EventsRestClient extends RestClient {

    void getSports(SportsRequest sportsRequest, StreamObserver<Sport> sportsObserver);

    void getEvent(EventRequest eventRequest, StreamObserver<Event> eventObserver);

    void getEvents(EventsRequest eventsRequest, StreamObserver<Event> eventsObserver);

    void getMarket(MarketRequest marketRequest, StreamObserver<Market> marketObserver);

    void getMarkets(MarketsRequest marketsRequest, StreamObserver<Market> marketsObserver);

    void getRunner(RunnerRequest runnerRequest, StreamObserver<Runner> runnerObserver);

    void getRunners(RunnersRequest runnersRequest, StreamObserver<Runner> runnersObserver);

}
