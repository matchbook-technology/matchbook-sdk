package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PageableResponseTest;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AggregatedMatchedBetsResponseTest extends PageableResponseTest<AggregatedMatchedBetsResponse, AggregatedMatchedBet> {

    @Override
    protected AggregatedMatchedBetsResponse newPageableResponse() {
        return new AggregatedMatchedBetsResponse();
    }

    @Override
    protected AggregatedMatchedBet mockItem() {
        return mock(AggregatedMatchedBet.class);
    }

    @Test
    @DisplayName("Set and get currency")
    void currencyTest() {
        Currency currency = Currency.HKD;
        unit.setCurrency(currency);
        assertThat(unit.getCurrency()).isEqualTo(currency);
    }

    @Test
    @DisplayName("Set and get odds type")
    void oddsTypeTest() {
        OddsType oddsType = OddsType.HK;
        unit.setOddsType(oddsType);
        assertThat(unit.getOddsType()).isEqualTo(oddsType);
    }

    @Test
    @DisplayName("Set and get exchange type")
    void exchangeTypeTest() {
        ExchangeType exchangeType = ExchangeType.BACK_LAY;
        unit.setExchangeType(exchangeType);
        assertThat(unit.getExchangeType()).isEqualTo(exchangeType);
    }

}
