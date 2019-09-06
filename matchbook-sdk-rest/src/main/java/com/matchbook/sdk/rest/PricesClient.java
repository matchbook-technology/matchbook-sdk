package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.PricesRequest;
import com.matchbook.sdk.rest.dtos.prices.readers.PricesResponseReader;

public interface PricesClient extends Client {

    void getPrices(PricesRequest pricesRequest, PricesResponseReader pricesReader, StreamObserver<Price> pricesObserver);

}
