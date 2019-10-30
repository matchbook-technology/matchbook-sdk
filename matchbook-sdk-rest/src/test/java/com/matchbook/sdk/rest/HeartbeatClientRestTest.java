package com.matchbook.sdk.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.ClientConfig;
import com.matchbook.sdk.rest.configs.ConnectionManager;
import com.matchbook.sdk.rest.configs.HttpClient;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatDeleteRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatPostRequest;

import java.io.IOException;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
class HeartbeatClientRestTest {

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private HttpClient httpClient;

    @Mock
    private Serializer serializer;

    protected HeartbeatClientRest unit;

    @BeforeEach
    void setUp() {
        final ClientConfig clientConfig = mock(ClientConfig.class);
        when(clientConfig.getSportsUrl()).thenReturn("https://matchbook.example.com/sports");
        when(connectionManager.getClientConfig()).thenReturn(clientConfig);
        when(connectionManager.getSerializer()).thenReturn(serializer);

        unit = new HeartbeatClientRest(connectionManager);
    }

    @Test
    @DisplayName("GET heartbeat")
    void getHeartbeatTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        HeartbeatGetRequest heartbeatGetRequest = mock(HeartbeatGetRequest.class);
        when(heartbeatGetRequest.resourcePath()).thenReturn("path");
        when(heartbeatGetRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<Heartbeat> streamObserver = (StreamObserver<Heartbeat>) mock(StreamObserver.class);

        unit.getHeartbeat(heartbeatGetRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("POST heartbeat successfully")
    void sendHeartbeatSuccessTest() throws IOException {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);
        when(serializer.writeObjectAsString(any(HeartbeatPostRequest.class))).thenReturn("{}");

        HeartbeatPostRequest heartbeatPostRequest = mock(HeartbeatPostRequest.class);
        when(heartbeatPostRequest.resourcePath()).thenReturn("path");
        StreamObserver<Heartbeat> streamObserver = (StreamObserver<Heartbeat>) mock(StreamObserver.class);

        unit.sendHeartbeat(heartbeatPostRequest, streamObserver);

        verify(httpClient).post(eq("https://matchbook.example.com/sports/path"), eq("{}"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("POST heartbeat parsing failure")
    void sendHeartbeatFailureTest() throws IOException {
        doThrow(IOException.class).when(serializer).writeObjectAsString(any(HeartbeatPostRequest.class));

        HeartbeatPostRequest heartbeatPostRequest = mock(HeartbeatPostRequest.class);
        when(heartbeatPostRequest.resourcePath()).thenReturn("path");
        StreamObserver<Heartbeat> streamObserver = (StreamObserver<Heartbeat>) mock(StreamObserver.class);

        unit.sendHeartbeat(heartbeatPostRequest, streamObserver);

        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
        verify(httpClient, never()).post(anyString(), anyString(), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("DELETE heartbeat")
    void unsubscribeHeartbeatTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        HeartbeatDeleteRequest heartbeatDeleteRequest = mock(HeartbeatDeleteRequest.class);
        when(heartbeatDeleteRequest.resourcePath()).thenReturn("path");
        when(heartbeatDeleteRequest.parameters()).thenReturn(Collections.emptyMap());
        StreamObserver<Heartbeat> streamObserver = (StreamObserver<Heartbeat>) mock(StreamObserver.class);

        unit.unsubscribeHeartbeat(heartbeatDeleteRequest, streamObserver);

        verify(httpClient).delete(eq("https://matchbook.example.com/sports/path"), any(RestResponseCallback.class));
    }

}
