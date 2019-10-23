package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PartiallyFailableResponseTest;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OffersResponseTest extends PartiallyFailableResponseTest<OffersResponse, Offer> {

    @Override
    protected OffersResponse newResponse() {
        return new OffersResponse();
    }

    @Override
    protected Offer mockItem() {
        return mock(Offer.class);
    }

    @Test
    @DisplayName("Set and get currency")
    void currencyTest() {
        Currency currency = Currency.USD;
        unit.setCurrency(currency);
        assertThat(unit.getCurrency()).isEqualTo(currency);
    }

    @Test
    @DisplayName("Set and get odds type")
    void oddsTypeTest() {
        OddsType oddsType = OddsType.US;
        unit.setOddsType(oddsType);
        assertThat(unit.getOddsType()).isEqualTo(oddsType);
    }

    @Test
    @DisplayName("Set and get exchange type")
    void exchangeTypeTest() {
        ExchangeType exchangeType = ExchangeType.BINARY;
        unit.setExchangeType(exchangeType);
        assertThat(unit.getExchangeType()).isEqualTo(exchangeType);
    }

}
