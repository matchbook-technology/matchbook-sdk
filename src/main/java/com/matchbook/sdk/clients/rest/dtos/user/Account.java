package com.matchbook.sdk.clients.rest.dtos.user;

import java.math.BigDecimal;

public class Account {

    private Long id;
    private String username;
    private BigDecimal balance;
    private BigDecimal exposure;
    private BigDecimal freeFunds;
    private BigDecimal commissionReserve;
    private String currency;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                ", exposure=" + exposure +
                ", freeFunds=" + freeFunds +
                ", commissionReserve=" + commissionReserve +
                ", currency='" + currency + '\'' +
                '}';
    }
}
