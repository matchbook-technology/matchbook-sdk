package com.matchbook.sdk.rest.dtos.user;

import com.matchbook.sdk.rest.dtos.RestResponse;
import com.matchbook.sdk.rest.dtos.offers.CommissionType;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

public class Account implements RestResponse<Account> {

    private Long id;
    private String username;
    private ExchangeType exchangeType;
    private OddsType oddsType;
    private Currency currency;
    private BigDecimal balance;
    private BigDecimal exposure;
    private BigDecimal freeFunds;
    private BigDecimal commissionReserve;
    private CommissionType commissionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public BigDecimal getExposure() {
        return exposure;
    }

    public void setExposure(BigDecimal exposure) {
        this.exposure = exposure;
    }

    public BigDecimal getFreeFunds() {
        return freeFunds;
    }

    public void setFreeFunds(BigDecimal freeFunds) {
        this.freeFunds = freeFunds;
    }

    public BigDecimal getCommissionReserve() {
        return commissionReserve;
    }

    public void setCommissionReserve(BigDecimal commissionReserve) {
        this.commissionReserve = commissionReserve;
    }

    public CommissionType getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(CommissionType commissionType) {
        this.commissionType = commissionType;
    }

    @Override
    public Collection<Account> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return Account.class.getSimpleName() + " {" +
                "id=" + id +
                ", username=" + username +
                ", exchangeType=" + exchangeType +
                ", oddsType=" + oddsType +
                ", currency=" + currency +
                ", balance=" + balance +
                ", exposure=" + exposure +
                ", freeFunds=" + freeFunds +
                ", commissionReserve=" + commissionReserve +
                ", commissionType=" + commissionType +
                "}";
    }
}
