package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.PageableResponse;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

public class AggregatedMatchedBetsResponse extends PageableResponse<AggregatedMatchedBet> {

    private Currency currency;
    private ExchangeType exchangeType;
    private OddsType oddsType;

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

    @Override
    public String toString() {
        return AggregatedMatchedBetsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", currency=" + currency +
                ", exchangeType=" + exchangeType +
                ", oddsType=" + oddsType +
                ", matchedBets=" + items +
                "}";
    }

}
