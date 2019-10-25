package com.matchbook.sdk.rest.dtos.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.matchbook.sdk.rest.dtos.prices.BasePricesRequestTest;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.PriceMode;
import com.matchbook.sdk.rest.dtos.prices.Side;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventRequestTest extends BasePricesRequestTest<EventRequest> {

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
                .includeEventParticipants(true)
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

    @Test
    @DisplayName("Check event ID")
    void eventIdTest() {
        Long actualEventId = unit.getEventId();
        assertThat(actualEventId).isEqualTo(emptyUnit.getEventId()).isEqualTo(eventId);
    }

    @Test
    @DisplayName("Check include event participants")
    void includeEventParticipantsTest() {
        assertThat(unit.includeEventParticipants()).isTrue();
        assertThat(emptyUnit.includeEventParticipants()).isFalse();
    }

    @Test
    @DisplayName("Check include prices")
    void includePricesTest() {
        assertThat(unit.includePrices()).isTrue();
        assertThat(emptyUnit.includePrices()).isFalse();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("events/" + eventId);
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(
                        tuple("include-event-participants", "true"),
                        tuple("include-prices", "true")
                );

        assertThat(emptyUnit.parameters())
                .doesNotContainKeys("include-prices")
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue).contains(
                        tuple("include-event-participants", "false")
                );
    }

}
