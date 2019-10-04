package com.matchbook.sdk.rest.dtos.user;

import com.matchbook.sdk.rest.dtos.RestResponse;

import java.math.BigDecimal;

public class Balance implements RestResponse {

    private Long id;
    private BigDecimal balance;
    private BigDecimal exposure;
    private BigDecimal freeFunds;
    private BigDecimal commissionReserve;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return Balance.class.getSimpleName() + " {" +
                "id=" + id +
                ", balance=" + balance +
                ", exposure=" + exposure +
                ", freeFunds=" + freeFunds +
                ", commissionReserve=" + commissionReserve +
                "}";
    }
}
