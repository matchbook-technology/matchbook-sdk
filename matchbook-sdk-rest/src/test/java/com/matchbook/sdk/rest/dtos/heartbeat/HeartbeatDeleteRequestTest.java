package com.matchbook.sdk.rest.dtos.heartbeat;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HeartbeatDeleteRequestTest extends RestRequestTest<HeartbeatDeleteRequest> {

    @Override
    protected HeartbeatDeleteRequest newRequest() {
        return new HeartbeatDeleteRequest.Builder().build();
    }

    @Override
    protected HeartbeatDeleteRequest newEmptyRequest() {
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
