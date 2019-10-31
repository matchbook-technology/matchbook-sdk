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
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBet;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBetsRequest;
import com.matchbook.sdk.rest.dtos.offers.CancelledMatchedBetsRequest;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;
import com.matchbook.sdk.rest.dtos.offers.Offer;
import com.matchbook.sdk.rest.dtos.offers.OfferDeleteRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferEditsGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferPutRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersDeleteRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersPostRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersPutRequest;
import com.matchbook.sdk.rest.dtos.offers.Position;
import com.matchbook.sdk.rest.dtos.offers.PositionsRequest;

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
class OffersClientRestTest {

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private HttpClient httpClient;

    @Mock
    private Serializer serializer;

    protected OffersClientRest unit;

    @BeforeEach
    void setUp() {
        final ClientConfig clientConfig = mock(ClientConfig.class);
        when(clientConfig.getSportsUrl()).thenReturn("https://matchbook.example.com/sports");
        when(connectionManager.getClientConfig()).thenReturn(clientConfig);
        when(connectionManager.getSerializer()).thenReturn(serializer);

        unit = new OffersClientRest(connectionManager);
    }

    @Test
    @DisplayName("GET offer")
    void getOfferTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        OfferGetRequest offerRequest = mock(OfferGetRequest.class);
        when(offerRequest.resourcePath()).thenReturn("path/42");
        when(offerRequest.parameters()).thenReturn(Collections.emptyMap());
        StreamObserver<Offer> streamObserver = (StreamObserver<Offer>) mock(StreamObserver.class);

        unit.getOffer(offerRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path/42"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET offers")
    void getOffersTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        OffersGetRequest offersRequest = mock(OffersGetRequest.class);
        when(offersRequest.resourcePath()).thenReturn("path");
        when(offersRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<Offer> streamObserver = (StreamObserver<Offer>) mock(StreamObserver.class);

        unit.getOffers(offersRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET offer edit")
    void getOfferEditTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        OfferEditGetRequest offerEditRequest = mock(OfferEditGetRequest.class);
        when(offerEditRequest.resourcePath()).thenReturn("path/42");
        when(offerEditRequest.parameters()).thenReturn(Collections.emptyMap());
        StreamObserver<OfferEdit> streamObserver = (StreamObserver<OfferEdit>) mock(StreamObserver.class);

        unit.getOfferEdit(offerEditRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path/42"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET offer edits")
    void getOfferEditsTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        OfferEditsGetRequest offerEditsRequest = mock(OfferEditsGetRequest.class);
        when(offerEditsRequest.resourcePath()).thenReturn("path");
        when(offerEditsRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<OfferEdit> streamObserver = (StreamObserver<OfferEdit>) mock(StreamObserver.class);

        unit.getOfferEdits(offerEditsRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET positions")
    void getPositionsTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        PositionsRequest positionRequest = mock(PositionsRequest.class);
        when(positionRequest.resourcePath()).thenReturn("path");
        when(positionRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<Position> streamObserver = (StreamObserver<Position>) mock(StreamObserver.class);

        unit.getPositions(positionRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET aggregated matched bets")
    void getAggregatedMatchedBetsTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        AggregatedMatchedBetsRequest aggregatedMatchedBetRequest = mock(AggregatedMatchedBetsRequest.class);
        when(aggregatedMatchedBetRequest.resourcePath()).thenReturn("path");
        when(aggregatedMatchedBetRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<AggregatedMatchedBet> streamObserver = (StreamObserver<AggregatedMatchedBet>) mock(StreamObserver.class);

        unit.getAggregatedMatchedBets(aggregatedMatchedBetRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("GET cancelled matched bets")
    void getCancelledMatchedBetsTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        CancelledMatchedBetsRequest cancelledMatchedBetRequest = mock(CancelledMatchedBetsRequest.class);
        when(cancelledMatchedBetRequest.resourcePath()).thenReturn("path");
        when(cancelledMatchedBetRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<MatchedBet> streamObserver = (StreamObserver<MatchedBet>) mock(StreamObserver.class);

        unit.getCancelledMatchedBets(cancelledMatchedBetRequest, streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/path?param=value"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("POST offers successfully")
    void submitOffersSuccessTest() throws IOException {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);
        when(serializer.writeObjectAsString(any(OffersPostRequest.class))).thenReturn("{}");

        OffersPostRequest offersPostRequest = mock(OffersPostRequest.class);
        when(offersPostRequest.resourcePath()).thenReturn("path");
        StreamObserver<Offer> streamObserver = (StreamObserver<Offer>) mock(StreamObserver.class);

        unit.submitOffers(offersPostRequest, streamObserver);

        verify(httpClient).post(eq("https://matchbook.example.com/sports/path"), eq("{}"),
                any(PartiallyFailableResponseCallback.class));
    }

    @Test
    @DisplayName("POST offers parsing failure")
    void submitOffersFailureTest() throws IOException {
        doThrow(IOException.class).when(serializer).writeObjectAsString(any(OffersPostRequest.class));

        OffersPostRequest offersPostRequest = mock(OffersPostRequest.class);
        when(offersPostRequest.resourcePath()).thenReturn("path");
        StreamObserver<Offer> streamObserver = (StreamObserver<Offer>) mock(StreamObserver.class);

        unit.submitOffers(offersPostRequest, streamObserver);

        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
        verify(httpClient, never()).post(anyString(), anyString(), any(PartiallyFailableResponseCallback.class));
    }

    @Test
    @DisplayName("PUT offer successfully")
    void editOfferSuccessTest() throws IOException {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);
        when(serializer.writeObjectAsString(any(OfferPutRequest.class))).thenReturn("{}");

        OfferPutRequest offerPutRequest = mock(OfferPutRequest.class);
        when(offerPutRequest.resourcePath()).thenReturn("path/42");
        StreamObserver<Offer> streamObserver = (StreamObserver<Offer>) mock(StreamObserver.class);

        unit.editOffer(offerPutRequest, streamObserver);

        verify(httpClient).put(eq("https://matchbook.example.com/sports/path/42"), eq("{}"),
                any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("PUT offer parsing failure")
    void editOfferFailureTest() throws IOException {
        doThrow(IOException.class).when(serializer).writeObjectAsString(any(OfferPutRequest.class));

        OfferPutRequest offerPutRequest = mock(OfferPutRequest.class);
        when(offerPutRequest.resourcePath()).thenReturn("path/42");
        StreamObserver<Offer> streamObserver = (StreamObserver<Offer>) mock(StreamObserver.class);

        unit.editOffer(offerPutRequest, streamObserver);

        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
        verify(httpClient, never()).put(anyString(), anyString(), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("PUT offers successfully")
    void editOffersSuccessTest() throws IOException {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);
        when(serializer.writeObjectAsString(any(OffersPutRequest.class))).thenReturn("{}");

        OffersPutRequest offersPutRequest = mock(OffersPutRequest.class);
        when(offersPutRequest.resourcePath()).thenReturn("path");
        StreamObserver<Offer> streamObserver = (StreamObserver<Offer>) mock(StreamObserver.class);

        unit.editOffers(offersPutRequest, streamObserver);

        verify(httpClient).put(eq("https://matchbook.example.com/sports/path"), eq("{}"),
                any(PartiallyFailableResponseCallback.class));
    }

    @Test
    @DisplayName("PUT offers parsing failure")
    void editOffersFailureTest() throws IOException {
        doThrow(IOException.class).when(serializer).writeObjectAsString(any(OffersPutRequest.class));

        OffersPutRequest offersPutRequest = mock(OffersPutRequest.class);
        when(offersPutRequest.resourcePath()).thenReturn("path");
        StreamObserver<Offer> streamObserver = (StreamObserver<Offer>) mock(StreamObserver.class);

        unit.editOffers(offersPutRequest, streamObserver);

        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
        verify(httpClient, never()).put(anyString(), anyString(), any(PartiallyFailableResponseCallback.class));
    }

    @Test
    @DisplayName("DELETE offer")
    void cancelOfferTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        OfferDeleteRequest offerDeleteRequest = mock(OfferDeleteRequest.class);
        when(offerDeleteRequest.resourcePath()).thenReturn("path");
        when(offerDeleteRequest.parameters()).thenReturn(Collections.emptyMap());
        StreamObserver<Offer> streamObserver = (StreamObserver<Offer>) mock(StreamObserver.class);

        unit.cancelOffer(offerDeleteRequest, streamObserver);

        verify(httpClient).delete(eq("https://matchbook.example.com/sports/path"), any(RestResponseCallback.class));
    }

    @Test
    @DisplayName("DELETE offers")
    void cancelOffersTest() {
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        OffersDeleteRequest offersDeleteRequest = mock(OffersDeleteRequest.class);
        when(offersDeleteRequest.resourcePath()).thenReturn("path");
        when(offersDeleteRequest.parameters()).thenReturn(Collections.singletonMap("param", "value"));
        StreamObserver<Offer> streamObserver = (StreamObserver<Offer>) mock(StreamObserver.class);

        unit.cancelOffers(offersDeleteRequest, streamObserver);

        verify(httpClient).delete(eq("https://matchbook.example.com/sports/path?param=value"),
                any(PartiallyFailableResponseCallback.class));
    }

}
