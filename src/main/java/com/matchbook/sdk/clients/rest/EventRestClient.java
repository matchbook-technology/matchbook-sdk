package com.matchbook.sdk.clients.rest;

import com.matchbook.sdk.clients.rest.dtos.events.EventRequest;
import com.matchbook.sdk.clients.rest.dtos.events.EventResponse;
import com.matchbook.sdk.clients.rest.dtos.events.EventsRequest;
import com.matchbook.sdk.clients.rest.dtos.events.EventsResponse;
import com.matchbook.sdk.clients.rest.dtos.events.MarketRequest;
import com.matchbook.sdk.clients.rest.dtos.events.MarketResponse;
import com.matchbook.sdk.clients.rest.dtos.events.MarketsRequest;
import com.matchbook.sdk.clients.rest.dtos.events.MarketsResponse;
import com.matchbook.sdk.clients.rest.dtos.events.RunnerRequest;
import com.matchbook.sdk.clients.rest.dtos.events.RunnerResponse;
import com.matchbook.sdk.clients.rest.dtos.events.RunnersRequest;
import com.matchbook.sdk.clients.rest.dtos.events.RunnersResponse;

public interface EventRestClient extends MatchbookRestClient {

    EventResponse getEvent(EventRequest eventRequest);

    EventsResponse getEvents(EventsRequest eventsRequest);

    MarketResponse getMarket(MarketRequest marketRequest);

    MarketsResponse getMarkets(MarketsRequest marketsRequest);

    RunnerResponse getRunner(RunnerRequest runnerRequest);

    RunnersResponse getRunners(RunnersRequest runnersRequest);

}
