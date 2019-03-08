package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.math.BigDecimal;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.prices.Currency;
import com.matchbook.sdk.core.clients.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.core.clients.rest.dtos.prices.OddsType;

public class Offers {

    private OddsType oddsType;
    private ExchangeType exchangeType;
    private Currency currency;
    private BigDecimal balance;
    private BigDecimal availableAmount;
    private BigDecimal exposure;
    private List<Offer> offers;

    public OddsType getOddsType() {
        return oddsType;
    }

    public void setOddsType(OddsType oddsType) {
        this.oddsType = oddsType;
    }

    public ExchangeType getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public BigDecimal getExposure() {
        return exposure;
    }

    public void setExposure(BigDecimal exposure) {
        this.exposure = exposure;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
