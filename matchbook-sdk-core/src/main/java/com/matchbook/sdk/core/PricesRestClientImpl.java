package com.matchbook.sdk.core;

import com.matchbook.sdk.common.StreamObserver;
import com.matchbook.sdk.core.dtos.prices.Price;
import com.matchbook.sdk.core.dtos.prices.PricesRequest;
import com.matchbook.sdk.core.dtos.prices.PricesResponse;

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
