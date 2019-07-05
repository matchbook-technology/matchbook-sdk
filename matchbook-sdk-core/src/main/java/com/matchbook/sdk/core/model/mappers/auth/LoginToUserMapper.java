package com.matchbook.sdk.core.model.mappers.auth;

import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.model.dataobjects.auth.Account;
import com.matchbook.sdk.core.model.dataobjects.auth.User;
import com.matchbook.sdk.core.model.dataobjects.prices.Currency;

public class LoginToUserMapper {

    public static User mapLoginDTOToUser(Login login) {
        User.Builder userBuilder = new User.Builder(login.getSessionToken(), login.getUserId());
        userBuilder.addAccount(mapAccount(login.getAccount()));

        return userBuilder.build();
    }

    private static Account mapAccount(com.matchbook.sdk.core.clients.rest.dtos.user.Account mbAccount) {
        Account.Builder accountBuilder = new Account.Builder(mbAccount.getId(), mbAccount.getUsername());
        accountBuilder.addBalance(mbAccount.getBalance());
        accountBuilder.addExposure(mbAccount.getExposure());
        accountBuilder.addFreeFunds(mbAccount.getFreeFunds());
        accountBuilder.addCommissionReserve(mbAccount.getCommissionReserve());
        accountBuilder.addCurrency(mapCurrency(mbAccount.getCurrency()));

        return accountBuilder.build();
    }

    private static Currency mapCurrency(com.matchbook.sdk.core.clients.rest.dtos.prices.Currency currency) {
        if (currency == null) {
            return null;
        }

        try {
            return Currency.valueOf(currency.name());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
