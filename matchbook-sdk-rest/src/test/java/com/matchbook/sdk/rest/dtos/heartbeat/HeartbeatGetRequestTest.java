package com.matchbook.sdk.rest.dtos.heartbeat;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HeartbeatGetRequestTest extends RestRequestTest<HeartbeatGetRequest> {

    @Override
    protected HeartbeatGetRequest newRequest() {
        return new HeartbeatGetRequest.Builder().build();
    }

    @Override
    protected HeartbeatGetRequest newEmptyRequest() {
        return newRequest();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("v1/heartbeat");
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).isEmpty();
    }

}
