package com.matchbook.sdk.rest.dtos.prices;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import com.matchbook.sdk.rest.dtos.RestResponse;

public class Price implements RestResponse<Price> {

    private ExchangeType exchangeType;
    private Side side;
    private OddsType oddsType;
    private Currency currency;
    private BigDecimal availableAmount;
    private BigDecimal odds;

    public ExchangeType getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public OddsType getOddsType() {
        return oddsType;
    }

    public void setOddsType(OddsType oddsType) {
        this.oddsType = oddsType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public BigDecimal getOdds() {
        return odds;
    }

    public void setOdds(BigDecimal odds) {
        this.odds = odds;
    }

    @Override
    public Set<Price> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return Price.class.getSimpleName() + " {" +
                "exchangeType=" + exchangeType +
                ", side=" + side +
                ", oddsType=" + oddsType +
                ", currency=" + currency +
                ", availableAmount=" + availableAmount +
                ", odds=" + odds +
                "}";
    }

}
