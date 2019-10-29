package com.matchbook.sdk.rest.dtos.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.Assertions.within;

import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.PageablePricesRequestTest;
import com.matchbook.sdk.rest.dtos.prices.PriceMode;
import com.matchbook.sdk.rest.dtos.prices.Side;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventsRequestTest extends PageablePricesRequestTest<EventsRequest> {

    private Instant after;
    private Instant before;
    private Set<Long> sportIds;
    private Set<Long> categoryIds;
    private Set<Long> eventIds;
    private Set<EventStatus> statuses;

    @Override
    @BeforeEach
    protected void setUp() {
        after = Instant.now().minus(1L, ChronoUnit.HOURS);
        before = Instant.now().plus(1L, ChronoUnit.HOURS);
        sportIds = Collections.singleton(9L);
        categoryIds = Collections.singleton(297063445660036L);
        eventIds = Collections.singleton(395729780570010L);
        statuses = Collections.singleton(EventStatus.OPEN);

        super.setUp();
    }

    @Override
    protected EventsRequest newPageablePricesRequest(OddsType oddsType, ExchangeType exchangeType, Side side,
            Currency currency, BigDecimal minimumLiquidity, PriceMode priceMode, int offset, int perPage) {
        return new EventsRequest.Builder()
                .after(after)
                .before(before)
                .sportIds(sportIds)
                .categoryIds(categoryIds)
                .eventIds(eventIds)
                .statuses(statuses)
                .includeEventParticipants(true)
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
    protected EventsRequest newEmptyRequest() {
        return new EventsRequest.Builder().build();
    }

    @Test
    @DisplayName("Check instant after limit")
    void afterTest() {
        Instant actualAfter = unit.getAfter();
        assertThat(actualAfter).isCloseTo(after, within(1L, ChronoUnit.SECONDS));

        assertThat(emptyUnit.getAfter()).isNull();
    }

    @Test
    @DisplayName("Check instant before limit")
    void beforeTest() {
        Instant actualBefore = unit.getBefore();
        assertThat(actualBefore).isCloseTo(before, within(1L, ChronoUnit.SECONDS));

        assertThat(emptyUnit.getBefore()).isNull();
    }

    @Test
    @DisplayName("Check sports IDs")
    void sportIdsTest() {
        Set<Long> actualSportIds = unit.getSportIds();
        assertThat(actualSportIds).containsExactlyInAnyOrderElementsOf(sportIds);

        assertThat(emptyUnit.getSportIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check category IDs")
    void categoryIdsTest() {
        Set<Long> actualCategoryIds = unit.getCategoryIds();
        assertThat(actualCategoryIds).containsExactlyInAnyOrderElementsOf(categoryIds);

        assertThat(emptyUnit.getCategoryIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check event IDs")
    void eventIdsTest() {
        Set<Long> actualEventIds = unit.getEventIds();
        assertThat(actualEventIds).containsExactlyInAnyOrderElementsOf(eventIds);

        assertThat(emptyUnit.getEventIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check statuses")
    void statusesTest() {
        Set<EventStatus> actualStatuses = unit.getStatuses();
        assertThat(actualStatuses).containsExactlyInAnyOrderElementsOf(statuses);

        assertThat(emptyUnit.getStatuses()).isNullOrEmpty();
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
        assertThat(path).isEqualTo("events");
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters())
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(
                        tuple("after", after.toString()),
                        tuple("before", before.toString()),
                        tuple("sport-ids", sportIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("category-ids", categoryIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("ids", eventIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("states", statuses.stream().map(EventStatus::name).collect(Collectors.joining(","))),
                        tuple("include-event-participants", "true"),
                        tuple("include-prices", "true")
                );

        assertThat(emptyUnit.parameters())
                .doesNotContainKeys("after", "before", "sport-ids", "category-ids", "ids", "states", "include-prices")
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(tuple("include-event-participants", "false"));
    }

}
