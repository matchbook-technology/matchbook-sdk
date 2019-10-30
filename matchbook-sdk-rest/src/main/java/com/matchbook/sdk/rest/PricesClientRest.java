package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.configs.ConnectionManager;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.PricesRequest;
import com.matchbook.sdk.rest.readers.prices.PricesReader;

public class PricesClientRest extends BaseClientRest implements PricesClient {

    public PricesClientRest(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void getPrices(PricesRequest pricesRequest, StreamObserver<Price> pricesObserver) {
        String url = buildSportsUrl(pricesRequest.resourcePath());
        getRequest(url, pricesRequest, pricesObserver, new PricesReader());
    }

}
