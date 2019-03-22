package com.matchbook.sdk.core.clients.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsRequest;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsResponse;
import com.matchbook.sdk.core.clients.rest.dtos.prices.Price;
import com.matchbook.sdk.core.clients.rest.dtos.prices.PricesRequest;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHTTPException;
import com.squareup.okhttp.Request;

public class PricesRestClientImpl extends AbstractRestClient implements PricesRestClient {

    private final ClientConnectionManager clientConnectionManager;
    private final ObjectWriter pricesRequestWriter;
    private final ObjectReader pricesResponseReader;

    public PricesRestClientImpl(ClientConnectionManager clientConnectionManager) {
        super(clientConnectionManager.getMapper());

        this.clientConnectionManager = clientConnectionManager;

        ObjectMapper objectMapper = clientConnectionManager.getMapper();
        this.pricesRequestWriter = objectMapper.writerFor(SportsRequest.class);
        this.pricesResponseReader = objectMapper.readerFor(SportsResponse.class);
    }

    @Override
    public void getPrices(PricesRequest pricesRequest, StreamObserver<Price> pricesObserver) {
        try {
            String path = "events/" + pricesRequest.getEventId() +
                    "/markets/" + pricesRequest.getMarketId() +
                    "/runners/" + pricesRequest.getRunnerId() + "/prices";
            String url = clientConnectionManager.getClientConfig().buildUrl(path);
            String requestBody = pricesRequestWriter.writeValueAsString(pricesRequest);
            Request request = buildRequest(url, requestBody);

            clientConnectionManager.getHttpClient()
                    .newCall(request)
                    .enqueue(new RestCallback<>(pricesObserver, pricesResponseReader));
        } catch (JsonProcessingException e) {
            pricesObserver.onError(new MatchbookSDKHTTPException(e.getCause()));
        }
    }

}
