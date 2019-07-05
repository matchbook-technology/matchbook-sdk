package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.matchbook.sdk.core.clients.rest.dtos.RestRequest;
import com.matchbook.sdk.core.clients.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.core.clients.rest.dtos.prices.OddsType;

public class OffersPostRequest implements RestRequest {

    private final OddsType oddsType;
    private final ExchangeType exchangeType;
    private final List<OfferPostRequest> offers;

    private OffersPostRequest(OffersPostRequest.Builder builder) {
        this.oddsType = builder.oddsType;
        this.exchangeType = builder.exchangeType;
        this.offers = builder.offers;
    }

    public OddsType getOddsType() {
        return oddsType;
    }

    public ExchangeType getExchangeType() {
        return exchangeType;
    }

    public List<OfferPostRequest> getOffers() {
        return offers;
    }

    @Override
    public String resourcePath() {
        return "v2/offers";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return OffersPostRequest.class.getSimpleName() + " {" +
                "oddsType=" + oddsType +
                ", exchangeType=" + exchangeType +
                ", offers=" + offers +
                "}";
    }

    public static class Builder {

        private final OddsType oddsType;
        private final ExchangeType exchangeType;
        private final List<OfferPostRequest> offers;

        public Builder(OddsType oddsType, ExchangeType exchangeType, List<OfferPostRequest> offers) {
            this.oddsType = oddsType;
            this.exchangeType = exchangeType;
            this.offers = offers;
        }

        public OffersPostRequest build() {
            return new OffersPostRequest(this);
        }

    }

}
