package com.matchbook.sdk.rest.dtos.prices;

import com.matchbook.sdk.rest.dtos.RestResponse;

import java.math.BigDecimal;

public class Price implements RestResponse {

    private ExchangeType exchangeType;
    private Side side;
    private OddsType oddsType;
    private Currency currency;
    private Double odds;
    private BigDecimal availableAmount;

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

    public Double getOdds() {
        return odds;
    }

    public void setOdds(Double odds) {
        this.odds = odds;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    @Override
    public String toString() {
        return Price.class.getSimpleName() + " {" +
                "exchangeType=" + exchangeType +
                ", side=" + side +
                ", oddsType=" + oddsType +
                ", currency=" + currency +
                ", odds=" + odds +
                ", availableAmount=" + availableAmount +
                "}";
    }

}
