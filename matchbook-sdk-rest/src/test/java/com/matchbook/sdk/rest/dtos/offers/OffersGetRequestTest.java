package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.matchbook.sdk.rest.dtos.PageableRequestTest;
import com.matchbook.sdk.rest.dtos.prices.Side;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OffersGetRequestTest extends PageableRequestTest<OffersGetRequest> {

    private Set<Long> sportIds;
    private Set<Long> eventIds;
    private Set<Long> marketIds;
    private Set<Long> runnersIds;
    private Set<OfferStatus> statuses;
    private Side side;
    private Integer interval;

    @Override
    @BeforeEach
    protected void setUp() {
        sportIds = Collections.singleton(9L);
        eventIds = Collections.singleton(395729780570010L);
        marketIds = Collections.singleton(395729860260010L);
        runnersIds = Collections.singleton(395729860800010L);
        statuses = Collections.singleton(OfferStatus.EDITED);
        side = Side.LAY;
        interval = 300;

        super.setUp();
    }

    @Override
    protected OffersGetRequest newPageableRequest(int offset, int perPage) {
        return new OffersGetRequest.Builder()
                .sportIds(sportIds)
                .eventIds(eventIds)
                .marketIds(marketIds)
                .runnersIds(runnersIds)
                .statuses(statuses)
                .side(side)
                .interval(interval)
                .includeEdits(true)
                .offset(offset)
                .perPage(perPage)
                .build();
    }

    @Override
    protected OffersGetRequest newEmptyRequest() {
        return new OffersGetRequest.Builder().build();
    }

    @Test
    @DisplayName("Check sports IDs")
    void sportIdsTest() {
        assertThat(unit.getSportIds()).containsExactlyInAnyOrderElementsOf(sportIds);
        assertThat(emptyUnit.getSportIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check event IDs")
    void eventIdsTest() {
        assertThat(unit.getEventIds()).containsExactlyInAnyOrderElementsOf(eventIds);
        assertThat(emptyUnit.getEventIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check market IDs")
    void marketIdsTest() {
        assertThat(unit.getMarketIds()).containsExactlyInAnyOrderElementsOf(marketIds);
        assertThat(emptyUnit.getMarketIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check runner IDs")
    void runnerIdsTest() {
        assertThat(unit.getRunnersIds()).containsExactlyInAnyOrderElementsOf(runnersIds);
        assertThat(emptyUnit.getRunnersIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check statuses")
    void statusesTest() {
        assertThat(unit.getStatuses()).containsExactlyInAnyOrderElementsOf(statuses);
        assertThat(emptyUnit.getStatuses()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check side")
    void sideTest() {
        assertThat(unit.getSide()).isEqualTo(side);
        assertThat(emptyUnit.getSide()).isNull();
    }

    @Test
    @DisplayName("Check interval")
    void intervalTest() {
        assertThat(unit.getInterval()).isEqualTo(interval);
        assertThat(emptyUnit.getInterval()).isNull();
    }

    @Test
    @DisplayName("Check include offer edits")
    void includeEditsTest() {
        assertThat(unit.includeEdits()).isTrue();
        assertThat(emptyUnit.includeEdits()).isFalse();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("v2/offers");
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters())
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(
                        tuple("sport-ids", sportIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("event-ids", eventIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("market-ids", marketIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("runner-ids", runnersIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("status", statuses.stream().map(OfferStatus::name).collect(Collectors.joining(","))),
                        tuple("side", side.name()),
                        tuple("interval", String.valueOf(interval)),
                        tuple("include-edits", "true")
                );

        assertThat(emptyUnit.parameters())
                .doesNotContainKeys("sport-ids", "event-ids", "market-ids", "runner-ids", "status", "side", "interval")
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(tuple("include-edits", "false"));
    }

}
