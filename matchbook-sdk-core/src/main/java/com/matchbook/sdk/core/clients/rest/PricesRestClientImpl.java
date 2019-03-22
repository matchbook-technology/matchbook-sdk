package com.matchbook.sdk.core.clients.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsResponse;
import com.matchbook.sdk.core.clients.rest.dtos.prices.Price;
import com.matchbook.sdk.core.clients.rest.dtos.prices.PricesRequest;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.squareup.okhttp.Request;

public class PricesRestClientImpl extends AbstractRestClient implements PricesRestClient {

    private final ClientConnectionManager clientConnectionManager;
    private final ObjectReader pricesResponseReader;

    public PricesRestClientImpl(ClientConnectionManager clientConnectionManager) {
        super(clientConnectionManager.getMapper());

        this.clientConnectionManager = clientConnectionManager;

        ObjectMapper objectMapper = clientConnectionManager.getMapper();
        this.pricesResponseReader = objectMapper.readerFor(SportsResponse.class);
    }

    @Override
    public void getPrices(PricesRequest pricesRequest, StreamObserver<Price> pricesObserver) {
        Request request = getRequest(pricesRequest.resourcePath(), pricesRequest.parameters());
        clientConnectionManager.getHttpClient()
                .newCall(request)
                .enqueue(new RestCallback<>(pricesObserver, pricesResponseReader));
    }

}
