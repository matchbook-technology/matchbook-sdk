package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.matchbook.sdk.rest.dtos.PageableRequestTest;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CancelledMatchedBetsRequestTest extends PageableRequestTest<CancelledMatchedBetsRequest> {

    @Override
    protected CancelledMatchedBetsRequest newPageableRequest(int offset, int perPage) {
        return new CancelledMatchedBetsRequest.Builder()
                .offset(offset)
                .perPage(perPage)
                .build();
    }

    @Test
    @DisplayName("Check status")
    void statusTest() {
        MatchedBetStatus actualStatus = unit.getStatus();
        assertThat(actualStatus).isEqualTo(MatchedBetStatus.CANCELLED);
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("bets");
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters())
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(tuple("status", MatchedBetStatus.CANCELLED.name()));
    }

}
