package com.matchbook.sdk.rest.dtos.prices;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestResponseTest;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PriceTest extends RestResponseTest<Price> {

    @Override
    protected Price newResponse() {
        return new Price();
    }

    @Test
    @DisplayName("Set and get exchange type")
    void exchangeTypeTest() {
        ExchangeType exchangeType = ExchangeType.BACK_LAY;
        unit.setExchangeType(exchangeType);
        assertThat(unit.getExchangeType()).isEqualTo(exchangeType);
    }

    @Test
    @DisplayName("Set and get side")
    void sideTest() {
        Side side = Side.WIN;
        unit.setSide(side);
        assertThat(unit.getSide()).isEqualTo(side);
    }

    @Test
    @DisplayName("Set and get odds type")
    void oddsTypeTest() {
        OddsType oddsType = OddsType.DECIMAL;
        unit.setOddsType(oddsType);
        assertThat(unit.getOddsType()).isEqualTo(oddsType);
    }

    @Test
    @DisplayName("Set and get currency")
    void currencyTest() {
        Currency currency = Currency.CAD;
        unit.setCurrency(currency);
        assertThat(unit.getCurrency()).isEqualTo(currency);
    }

    @Test
    @DisplayName("Set and get odds")
    void oddsTest() {
        Double odds = 3.5d;
        unit.setOdds(odds);
        assertThat(unit.getOdds()).isEqualTo(odds);
    }

    @Test
    @DisplayName("Set and get available amount")
    void availableAmountTest() {
        BigDecimal availableAmount = new BigDecimal("250");
        unit.setAvailableAmount(availableAmount);
        assertThat(unit.getAvailableAmount()).isEqualByComparingTo(availableAmount);
    }

}
