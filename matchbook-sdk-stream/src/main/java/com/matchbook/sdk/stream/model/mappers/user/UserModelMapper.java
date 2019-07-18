package com.matchbook.sdk.stream.model.mappers.user;

import com.matchbook.sdk.core.dtos.user.Login;
import com.matchbook.sdk.stream.model.dataobjects.prices.Currency;
import com.matchbook.sdk.stream.model.dataobjects.user.Account;
import com.matchbook.sdk.stream.model.dataobjects.user.Balance;
import com.matchbook.sdk.stream.model.dataobjects.user.User;

public class UserModelMapper {

    public Account mapAccount(com.matchbook.sdk.core.dtos.user.Account mbAccount) {

        Balance balance = new Balance.Builder(mbAccount.getId())
                .addBalance(mbAccount.getBalance())
                .addCommissionReserve(mbAccount.getCommissionReserve())
                .addExposure(mbAccount.getExposure())
                .addFreeFunds(mbAccount.getFreeFunds())
                .build();
        return new Account.Builder(mbAccount.getId(), mbAccount.getUsername())
                .addCurrency(mapCurrency(mbAccount.getCurrency()))
                .addBalance(balance)
                .build();
    }

    public Currency mapCurrency(com.matchbook.sdk.core.dtos.prices.Currency currency) {
        if (currency == null) {
            return null;
        }

        try {
            return Currency.valueOf(currency.name());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public Balance mapBalance(com.matchbook.sdk.core.dtos.user.Balance balanceDTO) {
        return new Balance.Builder(balanceDTO.getId())
                .addBalance(balanceDTO.getBalance())
                .addCommissionReserve(balanceDTO.getCommissionReserve())
                .addExposure(balanceDTO.getExposure())
                .addFreeFunds(balanceDTO.getFreeFunds())
                .build();
    }


    public User mapLogin(Login login) {
        return new User.Builder(login.getSessionToken(), login.getUserId())
                .addAccount(mapAccount(login.getAccount()))
                .build();
    }
}
