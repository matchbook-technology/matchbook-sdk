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

public class AggregatedMatchedBetsRequestTest extends PageableRequestTest<AggregatedMatchedBetsRequest> {

    private Set<Long> eventIds;
    private Set<Long> marketIds;
    private Set<Long> runnerIds;
    private Side side;
    private AggregationType aggregationType;

    @Override
    @BeforeEach
    protected void setUp() {
        eventIds = Collections.singleton(395729780570010L);
        marketIds = Collections.singleton(410499974510009L);
        runnerIds = Collections.singleton(410499974580010L);
        side = Side.BACK;
        aggregationType = AggregationType.SUMMARY;

        super.setUp();
    }

    @Override
    protected AggregatedMatchedBetsRequest newPageableRequest(int offset, int perPage) {
        return new AggregatedMatchedBetsRequest.Builder()
                .eventIds(eventIds)
                .marketIds(marketIds)
                .runnersIds(runnerIds)
                .side(side)
                .aggregationType(aggregationType)
                .offset(offset)
                .perPage(perPage)
                .build();
    }

    @Override
    protected AggregatedMatchedBetsRequest newEmptyRequest() {
        return new AggregatedMatchedBetsRequest.Builder().build();
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
        assertThat(actualRunnerIds).containsExactlyInAnyOrderElementsOf(runnerIds);

        assertThat(emptyUnit.getRunnersIds()).isNullOrEmpty();
    }

    @Test
    @DisplayName("Check side")
    void sideTest() {
        Side actualSide = unit.getSide();
        assertThat(actualSide).isEqualTo(side);

        assertThat(emptyUnit.getSide()).isNull();
    }

    @Test
    @DisplayName("Check aggregation type")
    void aggregationTypeTest() {
        AggregationType actualAggregationType = unit.getAggregationType();
        assertThat(actualAggregationType).isEqualTo(actualAggregationType);

        assertThat(emptyUnit.getAggregationType()).isNull();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("bets/matched/aggregated");
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(
                        tuple("event-ids", eventIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("market-ids", marketIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("runner-ids", runnerIds.stream().map(String::valueOf).collect(Collectors.joining(","))),
                        tuple("side", side.name()),
                        tuple("aggregation-type", aggregationType.name())
                );

        assertThat(emptyUnit.parameters())
                .doesNotContainKeys("event-ids", "market-ids", "runner-ids", "side", "aggregation-type");
    }

}
