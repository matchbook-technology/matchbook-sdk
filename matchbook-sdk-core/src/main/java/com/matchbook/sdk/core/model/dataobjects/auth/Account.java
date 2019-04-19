package com.matchbook.sdk.core.model.dataobjects.auth;

import java.math.BigDecimal;

import com.matchbook.sdk.core.model.dataobjects.prices.Currency;

public class Account {

    private Long id;
    private String username;
    private BigDecimal balance;
    private BigDecimal exposure;
    private BigDecimal freeFunds;
    private BigDecimal commissionReserve;
    private Currency currency;

    private Account(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.balance = builder.balance;
        this.exposure = builder.exposure;
        this.freeFunds = builder.freeFunds;
        this.commissionReserve = builder.commissionReserve;
        this.currency = builder.currency;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return Account.class.getSimpleName() + "{" +
                "id=" + id +
                ", username=" + username +
                ", balance=" + balance +
                ", exposure=" + exposure +
                ", freeFunds=" + freeFunds +
                ", commissionReserve=" + commissionReserve +
                ", currency=" + currency +
                "}";
    }

    public static class Builder {
        private Long id;
        private String username;
        private BigDecimal balance;
        private BigDecimal exposure;
        private BigDecimal freeFunds;
        private BigDecimal commissionReserve;
        private Currency currency;

        public Builder(Long id, String username) {
            this.id = id;
            this.username = username;
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

        public Builder addCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
