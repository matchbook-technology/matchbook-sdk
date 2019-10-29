package com.matchbook.sdk.rest.dtos.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.PageablePricesRequestTest;
import com.matchbook.sdk.rest.dtos.prices.PriceMode;
import com.matchbook.sdk.rest.dtos.prices.Side;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MarketsRequestTest extends PageablePricesRequestTest<MarketsRequest> {

    private Long eventId;
    private Set<MarketType> types;
    private Set<MarketStatus> statuses;

    @Override
    @BeforeEach
    protected void setUp() {
        eventId = 395729780570010L;
        types = Collections.singleton(MarketType.CORRECT_SCORE);
        statuses = Collections.singleton(MarketStatus.CLOSED);

        super.setUp();
    }

    @Override
    protected MarketsRequest newPageablePricesRequest(OddsType oddsType, ExchangeType exchangeType, Side side,
            Currency currency, BigDecimal minimumLiquidity, PriceMode priceMode, int offset, int perPage) {
        return new MarketsRequest.Builder(eventId)
                .types(types)
                .statuses(statuses)
                .includePrices(true)
                .oddsType(oddsType)
                .exchangeType(exchangeType)
                .side(side)
                .currency(currency)
                .minimumLiquidity(minimumLiquidity)
                .priceMode(priceMode)
                .offset(offset)
                .perPage(perPage)
                .build();
    }

    @Override
    protected MarketsRequest newEmptyRequest() {
        return new MarketsRequest.Builder(eventId).build();
    }

    @Test
    @DisplayName("Check event ID")
    void eventIdTest() {
        Long actualEventId = unit.getEventId();
        assertThat(actualEventId).isEqualTo(emptyUnit.getEventId()).isEqualTo(eventId);
    }

    @Test
    @DisplayName("Check types")
    void typesTest() {
        Set<MarketType> actualTypes = unit.getTypes();
        assertThat(actualTypes).containsExactlyInAnyOrderElementsOf(types);

        assertThat(emptyUnit.getTypes()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check statuses")
    void statusesTest() {
        Set<MarketStatus> actualStatuses = unit.getStatuses();
        assertThat(actualStatuses).containsExactlyInAnyOrderElementsOf(statuses);

        assertThat(emptyUnit.getStatuses()).isNullOrEmpty();
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
        assertThat(path).isEqualTo("events/" + eventId + "/markets");
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters())
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(
                        tuple("types", types.stream().map(MarketType::name).collect(Collectors.joining(","))),
                        tuple("states", statuses.stream().map(MarketStatus::name).collect(Collectors.joining(","))),
                        tuple("include-prices", "true")
                );

        assertThat(emptyUnit.parameters()).doesNotContainKeys("types", "states", "include-prices");
    }

}
