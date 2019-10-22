package com.matchbook.sdk.rest.dtos.events;

import com.matchbook.sdk.rest.dtos.prices.AbstractPricesRequestTest;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.PriceMode;
import com.matchbook.sdk.rest.dtos.prices.Side;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;

class EventRequestTest extends AbstractPricesRequestTest<EventRequest> {

    private Long eventId;

    @Override
    @BeforeEach
    protected void setUp() {
        eventId = 395729780570010L;
        super.setUp();
    }

    @Override
    protected EventRequest newPricesRequest(OddsType oddsType, ExchangeType exchangeType, Side side,
            Currency currency, BigDecimal minimumLiquidity, PriceMode priceMode) {
        return new EventRequest.Builder(eventId)
                .includePrices(true)
                .oddsType(oddsType)
                .exchangeType(exchangeType)
                .side(side)
                .currency(currency)
                .minimumLiquidity(minimumLiquidity)
                .priceMode(priceMode)
                .build();
    }

    @Override
    protected EventRequest newEmptyRequest() {
        return new EventRequest.Builder(eventId).build();
    }

}
