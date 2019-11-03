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
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.PricesRequest;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
class PricesClientRestTest {

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private HttpClient httpClient;

    private PricesClientRest unit;

    @BeforeEach
    void setUp() {
        final ClientConfig clientConfig = mock(ClientConfig.class);
        when(clientConfig.getSportsUrl()).thenReturn("https://matchbook.example.com/sports");
        when(connectionManager.getClientConfig()).thenReturn(clientConfig);

        final Serializer serializer = mock(Serializer.class);
        when(connectionManager.getSerializer()).thenReturn(serializer);
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        unit = new PricesClientRest(connectionManager);
    }

    @Test
    @DisplayName("GET prices")
    void getPricesTest() {
        PricesRequest pricesRequest = mock(PricesRequest.class);
        when(pricesRequest.resourcePath()).thenReturn("path");
        when(pricesRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<Price> streamObserver = (StreamObserver<Price>) mock(StreamObserver.class);

        unit.getPrices(pricesRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(ResponseCallback.class));
    }

}
