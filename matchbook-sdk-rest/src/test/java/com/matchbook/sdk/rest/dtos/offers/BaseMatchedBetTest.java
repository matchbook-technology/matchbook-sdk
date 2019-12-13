package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import com.matchbook.sdk.rest.dtos.RestResponseTest;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

abstract class BaseMatchedBetTest<T extends BaseMatchedBet> extends RestResponseTest<T> {

    @Test
    @DisplayName("Set and get status")
    void statusTest() {
        MatchedBetStatus status = MatchedBetStatus.PAID;
        unit.setStatus(status);
        assertThat(unit.getStatus()).isEqualTo(status);
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
        Double odds = 1.5d;
        unit.setOdds(odds);
        assertThat(unit.getOdds()).isEqualTo(odds);
    }

    @Test
    @DisplayName("Set and get decimal odds")
    void decimalOddsTest() {
        Double decimalOdds = 1.5d;
        unit.setDecimalOdds(decimalOdds);
        assertThat(unit.getDecimalOdds()).isEqualTo(decimalOdds);
    }

    @Test
    @DisplayName("Set and get stake")
    void stakeTest() {
        BigDecimal stake = new BigDecimal("130");
        unit.setStake(stake);
        assertThat(unit.getStake()).isEqualByComparingTo(stake);
    }

    @Test
    @DisplayName("Set and get potential profit")
    void potentialProfitTest() {
        BigDecimal potentialProfit = new BigDecimal("195");
        unit.setPotentialProfit(potentialProfit);
        assertThat(unit.getPotentialProfit()).isEqualByComparingTo(potentialProfit);
    }

    @Test
    @DisplayName("Set and get potential liability")
    void potentialLiabilityTest() {
        BigDecimal potentialLiability = new BigDecimal("130");
        unit.setPotentialLiability(potentialLiability);
        assertThat(unit.getPotentialLiability()).isEqualByComparingTo(potentialLiability);
    }

}
