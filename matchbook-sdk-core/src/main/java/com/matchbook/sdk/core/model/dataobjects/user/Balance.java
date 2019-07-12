package com.matchbook.sdk.core.model.dataobjects.user;

import java.math.BigDecimal;

public class Balance {

    private final Long id;
    private final BigDecimal balance;
    private final BigDecimal exposure;
    private final BigDecimal freeFunds;
    private final BigDecimal commissionReserve;

    private Balance(Builder builder) {
        this.id = builder.id;
        this.balance = builder.balance;
        this.exposure = builder.exposure;
        this.freeFunds = builder.freeFunds;
        this.commissionReserve = builder.commissionReserve;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getExposure() {
        return exposure;
    }

    public BigDecimal getFreeFunds() {
        return freeFunds;
    }

    public BigDecimal getCommissionReserve() {
        return commissionReserve;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", balance=" + balance +
                ", exposure=" + exposure +
                ", freeFunds=" + freeFunds +
                ", commissionReserve=" + commissionReserve +
                '}';
    }

    public static class Builder {
        private Long id;
        private BigDecimal balance;
        private BigDecimal exposure;
        private BigDecimal freeFunds;
        private BigDecimal commissionReserve;

        public Builder(Long id) {
            this.id = id;
        }

        public Builder addBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder addExposure(BigDecimal exposure) {
            this.exposure = exposure;
            return this;
        }

        public Builder addFreeFunds(BigDecimal freeFunds) {
            this.freeFunds = freeFunds;
            return this;
        }

        public Builder addCommissionReserve(BigDecimal commissionReserve) {
            this.commissionReserve = commissionReserve;
            return this;
        }


        public Balance build() {
            return new Balance(this);
        }
    }
}
