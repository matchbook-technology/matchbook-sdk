package com.matchbook.sdk.rest.dtos.prices;

import java.math.BigDecimal;

public class Price {

    private Side side;
    private OddsType oddsType;
    private Currency currency;
    private BigDecimal availableAmount;
    private BigDecimal odds;

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
    public String toString() {
        return Price.class.getSimpleName() + " {" +
                "side=" + side +
                ", oddsType=" + oddsType +
                ", currency=" + currency +
                ", availableAmount=" + availableAmount +
                ", odds=" + odds +
                "}";
    }

}
