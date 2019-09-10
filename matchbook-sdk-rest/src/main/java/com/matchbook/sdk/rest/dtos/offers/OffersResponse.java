package com.matchbook.sdk.rest.dtos.offers;

import java.util.Collection;
import java.util.List;

import com.matchbook.sdk.rest.dtos.PageableResponse;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

public class OffersResponse extends PageableResponse<Offer> {

    private Currency currency;
    private ExchangeType exchangeType;
    private OddsType oddsType;
    private List<Offer> offers;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public ExchangeType getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
    }

    public OddsType getOddsType() {
        return oddsType;
    }

    public void setOddsType(OddsType oddsType) {
        this.oddsType = oddsType;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public Collection<Offer> getContent() {
        return offers;
    }

    @Override
    public String toString() {
        return OffersResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", currency=" + currency +
                ", exchangeType=" + exchangeType +
                ", oddsType=" + oddsType +
                ", offers=" + offers +
                "}";
    }

}
