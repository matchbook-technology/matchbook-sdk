package com.matchbook.sdk.core;

import com.matchbook.sdk.common.StreamObserver;
import com.matchbook.sdk.core.dtos.prices.Price;
import com.matchbook.sdk.core.dtos.prices.PricesRequest;

public interface PricesRestClient extends RestClient {

    void getPrices(PricesRequest pricesRequest, StreamObserver<Price> pricesObserver);

}
