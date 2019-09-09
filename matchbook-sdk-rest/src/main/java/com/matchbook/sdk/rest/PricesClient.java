package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.PricesRequest;

public interface PricesClient extends Client {

    void getPrices(PricesRequest pricesRequest, StreamObserver<Price> pricesObserver);

}
