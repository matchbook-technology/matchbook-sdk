package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Collection;

import com.matchbook.sdk.core.clients.rest.dtos.PageableResponse;

public class BalanceResponse extends PageableResponse<Balance> {

    private Balance balance;

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    @Override
    public Collection<Balance> getContent() {
        return null;
    }

    @Override
    public String toString() {
        return "BalanceResponse{" +
                "balance=" + balance +
                '}';
    }
}
