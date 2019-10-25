package com.matchbook.sdk.rest.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import com.matchbook.sdk.rest.dtos.RestResponseTest;
import com.matchbook.sdk.rest.dtos.offers.CommissionType;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest extends RestResponseTest<Account> {

    @Override
    protected Account newResponse() {
        return new Account();
    }

    @Test
    @DisplayName("Set and get account ID")
    void idTest() {
        Long actualId = 4242L;
        unit.setId(actualId);
        assertThat(unit.getId()).isEqualTo(actualId);
    }

    @Test
    @DisplayName("Set and get username")
    void usernameTest() {
        String username = "john_doe";
        unit.setUsername(username);
        assertThat(unit.getUsername()).isEqualTo(username);
    }

    @Test
    @DisplayName("Set and get exchange type")
    void exchangeTypeTest() {
        ExchangeType exchangeType = ExchangeType.BACK_LAY;
        unit.setExchangeType(exchangeType);
        assertThat(unit.getExchangeType()).isEqualTo(exchangeType);
    }

    @Test
    @DisplayName("Set and get odds type")
    void oddsTypeTest() {
        OddsType oddsType = OddsType.US;
        unit.setOddsType(oddsType);
        assertThat(unit.getOddsType()).isEqualTo(oddsType);
    }

    @Test
    @DisplayName("Set and get currency")
    void currencyTest() {
        Currency currency = Currency.USD;
        unit.setCurrency(currency);
        assertThat(unit.getCurrency()).isEqualTo(currency);
    }

    @Test
    @DisplayName("Set and get balance")
    void balanceTest() {
        BigDecimal balance = new BigDecimal("1502.39");
        unit.setBalance(balance);
        assertThat(unit.getBalance()).isEqualByComparingTo(balance);
    }

    @Test
    @DisplayName("Set and get exposure")
    void exposureTest() {
        BigDecimal exposure = new BigDecimal("207.19");
        unit.setExposure(exposure);
        assertThat(unit.getExposure()).isEqualByComparingTo(exposure);
    }

    @Test
    @DisplayName("Set and get free funds")
    void freeFundsTest() {
        BigDecimal freeFunds = new BigDecimal("1295.20");
        unit.setFreeFunds(freeFunds);
        assertThat(unit.getFreeFunds()).isEqualByComparingTo(freeFunds);
    }

    @Test
    @DisplayName("Set and get commission reserve")
    void commissionReserveTest() {
        BigDecimal commissionReserve = BigDecimal.ZERO;
        unit.setCommissionReserve(commissionReserve);
        assertThat(unit.getCommissionReserve()).isEqualByComparingTo(commissionReserve);
    }

    @Test
    @DisplayName("Set and get commission type")
    void commissionTypeTest() {
        CommissionType commissionType = CommissionType.NET_WIN;
        unit.setCommissionType(commissionType);
        assertThat(unit.getCommissionType()).isEqualTo(commissionType);
    }

}
