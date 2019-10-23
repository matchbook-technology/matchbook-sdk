package com.matchbook.sdk.rest.dtos.prices;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PricesRequestTest extends BasePricesRequestTest<PricesRequest> {

    private Long eventId;
    private Long marketId;
    private Long runnerId;

    @Override
    @BeforeEach
    protected void setUp() {
        eventId = 395729780570010L;
        marketId = 395729860260010L;
        runnerId = 395729860800010L;

        super.setUp();
    }

    @Override
    protected PricesRequest newPricesRequest(OddsType oddsType, ExchangeType exchangeType, Side side,
            Currency currency, BigDecimal minimumLiquidity, PriceMode priceMode) {
        return new PricesRequest.Builder(eventId, marketId, runnerId)
                .oddsType(oddsType)
                .exchangeType(exchangeType)
                .side(side)
                .currency(currency)
                .minimumLiquidity(minimumLiquidity)
                .priceMode(priceMode)
                .build();
    }

    @Override
    protected PricesRequest newEmptyRequest() {
        return new PricesRequest.Builder(eventId, marketId, runnerId).build();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("events/" + eventId + "/markets/" + marketId + "/runners/" + runnerId + "/prices");
    }

    @Test
    @DisplayName("Check event ID")
    void eventIdTest() {
        Long actualEventId = unit.getEventId();
        assertThat(actualEventId).isEqualTo(eventId);
    }

    @Test
    @DisplayName("Check market ID")
    void marketIdTest() {
        Long actualMarketId = unit.getMarketId();
        assertThat(actualMarketId).isEqualTo(marketId);
    }

    @Test
    @DisplayName("Check runner ID")
    void runnerIdTest() {
        Long actualRunnerId = unit.getRunnerId();
        assertThat(actualRunnerId).isEqualTo(runnerId);
    }

}
