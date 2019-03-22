package com.matchbook.sdk.core.clients.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        String path = getPricesPath(pricesRequest);
        Map<String, String> parameters = getPricesParameters(pricesRequest);
        Request request = getRequest(path, parameters);

        clientConnectionManager.getHttpClient()
                .newCall(request)
                .enqueue(new RestCallback<>(pricesObserver, pricesResponseReader));
    }

    private String getPricesPath(PricesRequest pricesRequest) {
        String path = "events/" + pricesRequest.getEventId() +
                "/markets/" + pricesRequest.getMarketId() +
                "/runners/" + pricesRequest.getRunnerId() + "/prices";
        return clientConnectionManager.getClientConfig().buildUrl(path);
    }

    private Map<String, String> getPricesParameters(PricesRequest pricesRequest) {
        Map<String, String> parameters = new HashMap<>();
        if (Objects.nonNull(pricesRequest.getCurrency())) {
            parameters.put("currency", pricesRequest.getCurrency().name());
        }
        if (Objects.nonNull(pricesRequest.getExchangeType())) {
            parameters.put("exchange-type", pricesRequest.getExchangeType().name());
        }
        if (Objects.nonNull(pricesRequest.getOddsType())) {
            parameters.put("odds-type", pricesRequest.getOddsType().name());
        }
        if (Objects.nonNull(pricesRequest.getSide())) {
            parameters.put("side", pricesRequest.getSide().name());
        }
        if (Objects.nonNull(pricesRequest.getMinimumLiquidity())) {
            parameters.put("minimum-liquidity", pricesRequest.getMinimumLiquidity().toPlainString());
        }
        if (Objects.nonNull(pricesRequest.getPriceMode())) {
            parameters.put("price-mode", pricesRequest.getPriceMode().name());
        }
        return parameters;
    }

}
