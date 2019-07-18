package com.matchbook.sdk.core;

import com.matchbook.sdk.common.StreamObserver;
import com.matchbook.sdk.core.dtos.events.Event;
import com.matchbook.sdk.core.dtos.events.EventRequest;
import com.matchbook.sdk.core.dtos.events.EventsRequest;
import com.matchbook.sdk.core.dtos.events.Market;
import com.matchbook.sdk.core.dtos.events.MarketRequest;
import com.matchbook.sdk.core.dtos.events.MarketsRequest;
import com.matchbook.sdk.core.dtos.events.Runner;
import com.matchbook.sdk.core.dtos.events.RunnerRequest;
import com.matchbook.sdk.core.dtos.events.RunnersRequest;
import com.matchbook.sdk.core.dtos.events.Sport;
import com.matchbook.sdk.core.dtos.events.SportsRequest;

public interface EventsRestClient extends RestClient {

    void getSports(SportsRequest sportsRequest, StreamObserver<Sport> sportsObserver);

    void getEvent(EventRequest eventRequest, StreamObserver<Event> eventObserver);

    void getEvents(EventsRequest eventsRequest, StreamObserver<Event> eventsObserver);

    void getMarket(MarketRequest marketRequest, StreamObserver<Market> marketObserver);

    void getMarkets(MarketsRequest marketsRequest, StreamObserver<Market> marketsObserver);

    void getRunner(RunnerRequest runnerRequest, StreamObserver<Runner> runnerObserver);

    void getRunners(RunnersRequest runnersRequest, StreamObserver<Runner> runnersObserver);

}
