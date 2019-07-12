package com.matchbook.sdk.core.model.dataobjects.user;

import com.matchbook.sdk.core.model.dataobjects.prices.Currency;

public class Account {

    private final Long id;
    private final String username;
    private final Currency currency;
    private final Balance balance;

    private Account(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.currency = builder.currency;
        this.balance = builder.balance;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Balance getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                '}';
    }

    public static class Builder {
        private Long id;
        private String username;
        private Currency currency;
        private Balance balance;

        public Builder(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        public Builder addBalance(Balance balance) {
            this.balance = balance;
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
