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

class RunnersRequestTest extends PageablePricesRequestTest<RunnersRequest> {

    private Long eventId;
    private Long marketId;
    private Set<RunnerStatus> statuses;

    @Override
    @BeforeEach
    protected void setUp() {
        eventId = 395729780570010L;
        marketId = 395729860260010L;
        statuses = Collections.singleton(RunnerStatus.OPEN);

        super.setUp();
    }

    @Override
    protected RunnersRequest newPageablePricesRequest(OddsType oddsType, ExchangeType exchangeType, Side side,
            Currency currency, BigDecimal minimumLiquidity, PriceMode priceMode, int offset, int perPage) {
        return new RunnersRequest.Builder(eventId, marketId)
                .statuses(statuses)
                .includeWithdrawn(false)
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
    protected RunnersRequest newEmptyRequest() {
        return new RunnersRequest.Builder(eventId, marketId).build();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("events/" + eventId + "/markets/" + marketId + "/runners");
    }

    @Test
    @DisplayName("Check event ID")
    void eventIdTest() {
        Long actualEventId = unit.getEventId();
        assertThat(actualEventId).isEqualTo(emptyUnit.getEventId()).isEqualTo(eventId);
    }

    @Test
    @DisplayName("Check market ID")
    void marketIdTest() {
        Long actualMarketId = unit.getMarketId();
        assertThat(actualMarketId).isEqualTo(emptyUnit.getMarketId()).isEqualTo(marketId);
    }

    @Test
    @DisplayName("Check statuses")
    void statusesTest() {
        Set<RunnerStatus> actualStatuses = unit.getStatuses();
        assertThat(unit.getStatuses()).containsExactlyInAnyOrderElementsOf(actualStatuses);

        assertThat(emptyUnit.getStatuses()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check include withdrawn")
    void includeWithdrawnTest() {
        assertThat(unit.includeWithdrawn()).isFalse();
        assertThat(emptyUnit.includeWithdrawn()).isTrue();
    }

    @Test
    @DisplayName("Check include prices")
    void includePricesTest() {
        assertThat(unit.includePrices()).isTrue();
        assertThat(emptyUnit.includePrices()).isFalse();
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(
                        tuple("states", statuses.stream().map(RunnerStatus::name).collect(Collectors.joining(","))),
                        tuple("include-withdrawn", "false"),
                        tuple("include-prices", "true")
                );

        assertThat(emptyUnit.parameters()).doesNotContainKeys("types", "states", "include-prices")
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue).contains(
                        tuple("include-withdrawn", "true")
                );
    }

}
