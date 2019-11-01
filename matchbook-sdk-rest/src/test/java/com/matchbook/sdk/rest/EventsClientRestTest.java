package com.matchbook.sdk.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.configs.ClientConfig;
import com.matchbook.sdk.rest.configs.ConnectionManager;
import com.matchbook.sdk.rest.configs.HttpClient;
import com.matchbook.sdk.rest.configs.Serializer;
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

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
class EventsClientRestTest {

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private HttpClient httpClient;

    private EventsClientRest unit;

    @BeforeEach
    void setUp() {
        final ClientConfig clientConfig = mock(ClientConfig.class);
        when(clientConfig.getSportsUrl()).thenReturn("https://matchbook.example.com/sports");
        when(connectionManager.getClientConfig()).thenReturn(clientConfig);

        final Serializer serializer = mock(Serializer.class);
        when(connectionManager.getSerializer()).thenReturn(serializer);
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        unit = new EventsClientRest(connectionManager);
    }

    @Test
    @DisplayName("GET sports")
    void getSportsTest() {
        SportsRequest sportsRequest = mock(SportsRequest.class);
        when(sportsRequest.resourcePath()).thenReturn("path");
        when(sportsRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<Sport> streamObserver = (StreamObserver<Sport>) mock(StreamObserver.class);

        unit.getSports(sportsRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET event")
    void getEventTest() {
        EventRequest eventRequest = mock(EventRequest.class);
        when(eventRequest.resourcePath()).thenReturn("path/42");
        when(eventRequest.parameters()).thenReturn(Collections.emptyMap());
        StreamObserver<Event> streamObserver = (StreamObserver<Event>) mock(StreamObserver.class);

        unit.getEvent(eventRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path/42"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET events")
    void getEventsTest() {
        EventsRequest eventsRequest = mock(EventsRequest.class);
        when(eventsRequest.resourcePath()).thenReturn("path");
        when(eventsRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<Event> streamObserver = (StreamObserver<Event>) mock(StreamObserver.class);

        unit.getEvents(eventsRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET market")
    void getMarketTest() {
        MarketRequest marketRequest = mock(MarketRequest.class);
        when(marketRequest.resourcePath()).thenReturn("path/42");
        when(marketRequest.parameters()).thenReturn(Collections.emptyMap());
        StreamObserver<Market> streamObserver = (StreamObserver<Market>) mock(StreamObserver.class);

        unit.getMarket(marketRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path/42"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET markets")
    void getMarketsTest() {
        MarketsRequest marketsRequest = mock(MarketsRequest.class);
        when(marketsRequest.resourcePath()).thenReturn("path");
        when(marketsRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<Market> streamObserver = (StreamObserver<Market>) mock(StreamObserver.class);

        unit.getMarkets(marketsRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET runner")
    void getRunnerTest() {
        RunnerRequest runnerRequest = mock(RunnerRequest.class);
        when(runnerRequest.resourcePath()).thenReturn("path/42");
        when(runnerRequest.parameters()).thenReturn(Collections.emptyMap());
        StreamObserver<Runner> streamObserver = (StreamObserver<Runner>) mock(StreamObserver.class);

        unit.getRunner(runnerRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path/42"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET runners")
    void getRunnersTest() {
        RunnersRequest runnersRequest = mock(RunnersRequest.class);
        when(runnersRequest.resourcePath()).thenReturn("path");
        when(runnersRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<Runner> streamObserver = (StreamObserver<Runner>) mock(StreamObserver.class);

        unit.getRunners(runnersRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(RestResponseCallback.class));
    }

}
