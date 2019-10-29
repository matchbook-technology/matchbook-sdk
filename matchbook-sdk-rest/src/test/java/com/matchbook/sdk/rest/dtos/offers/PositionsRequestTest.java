package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.matchbook.sdk.rest.dtos.PageableRequestTest;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionsRequestTest extends PageableRequestTest<PositionsRequest> {

    private Set<Long> eventIds;
    private Set<Long> marketIds;
    private Set<Long> runnersIds;

    @Override
    @BeforeEach
    protected void setUp() {
        eventIds = Collections.singleton(395729780570010L);
        marketIds = Collections.singleton(395729860260010L);
        runnersIds = Collections.singleton(395729860800010L);

        super.setUp();
    }

    @Override
    protected PositionsRequest newPageableRequest(int offset, int perPage) {
        return new PositionsRequest.Builder()
                .eventIds(eventIds)
                .marketIds(marketIds)
                .runnersIds(runnersIds)
                .offset(offset)
                .perPage(perPage)
                .build();
    }

    @Override
    protected PositionsRequest newEmptyRequest() {
        return new PositionsRequest.Builder().build();
    }

    @Test
    @DisplayName("Check event IDs")
    void eventIdsTest() {
        Set<Long> actualEventIds = unit.getEventIds();
        assertThat(actualEventIds).containsExactlyInAnyOrderElementsOf(eventIds);

        assertThat(emptyUnit.getEventIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check market IDs")
    void marketIdsTest() {
        Set<Long> actualMarketIds = unit.getMarketIds();
        assertThat(actualMarketIds).containsExactlyInAnyOrderElementsOf(marketIds);

        assertThat(emptyUnit.getMarketIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check runner IDs")
    void runnerIdsTest() {
        Set<Long> actualRunnerIds = unit.getRunnersIds();
        assertThat(actualRunnerIds).containsExactlyInAnyOrderElementsOf(runnersIds);

        assertThat(emptyUnit.getRunnersIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("account/positions");
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters())
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(
                        tuple("event-ids", eventIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("market-ids", marketIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("runner-ids", runnersIds.stream().map(String::valueOf).collect(Collectors.joining(",")))
                );

        assertThat(emptyUnit.parameters()).doesNotContainKeys("event-ids", "market-ids", "runner-ids");
    }

}
