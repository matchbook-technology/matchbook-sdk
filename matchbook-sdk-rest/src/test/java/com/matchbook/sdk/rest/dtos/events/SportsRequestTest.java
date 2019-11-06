package com.matchbook.sdk.rest.dtos.events;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.PageableRequestTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SportsRequestTest extends PageableRequestTest<SportsRequest> {

    @Override
    protected SportsRequest newPageableRequest(int offset, int perPage) {
        return new SportsRequest.Builder()
                .offset(offset)
                .perPage(perPage)
                .build();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("lookups/sports");
    }

}
