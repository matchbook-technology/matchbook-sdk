package com.matchbook.sdk.rest.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import com.matchbook.sdk.rest.dtos.RestResponseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BalanceTest extends RestResponseTest<Balance> {

    @Override
    protected Balance newResponse() {
        return new Balance();
    }

    @Test
    @DisplayName("Set and get account ID")
    void idTest() {
        Long accountId = 4242L;
        unit.setId(accountId);
        assertThat(unit.getId()).isEqualTo(accountId);
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

}
