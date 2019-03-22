package com.matchbook.sdk.core.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.prices.Price;
import com.matchbook.sdk.core.clients.rest.dtos.prices.PricesRequest;

public interface PricesRestClient extends RestClient {

    void getPrices(PricesRequest pricesRequest, StreamObserver<Price> pricesObserver);

}
