package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Collection;
import java.util.Collections;

import com.matchbook.sdk.core.clients.rest.dtos.PageableResponse;

public class AccountResponse extends PageableResponse<Account> {

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public Collection<Account> getContent() {
        return Collections.singleton(account);
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                "account=" + account +
                ", total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                '}';
    }
}
