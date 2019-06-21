package com.matchbook.sdk.core.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.prices.Price;
import com.matchbook.sdk.core.clients.rest.dtos.prices.PricesRequest;
import com.matchbook.sdk.core.clients.rest.dtos.prices.PricesResponse;
import com.matchbook.sdk.core.configs.ConnectionManager;

public class PricesRestClientImpl extends AbstractRestClient implements PricesRestClient {

    public PricesRestClientImpl(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void getPrices(PricesRequest pricesRequest, StreamObserver<Price> pricesObserver) {
        String url = buildSportsUrl(pricesRequest.resourcePath());
        getRequest(url, pricesRequest, pricesObserver, PricesResponse.class);
    }

}
