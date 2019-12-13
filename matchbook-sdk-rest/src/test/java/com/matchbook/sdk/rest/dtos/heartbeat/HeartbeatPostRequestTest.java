package com.matchbook.sdk.rest.dtos.heartbeat;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HeartbeatPostRequestTest extends RestRequestTest<HeartbeatPostRequest> {

    private Integer timeout;

    @Override
    @BeforeEach
    protected void setUp() {
        timeout = 45;

        super.setUp();
    }

    @Override
    protected HeartbeatPostRequest newRequest() {
        return new HeartbeatPostRequest.Builder(timeout).build();
    }

    @Test
    @DisplayName("Check timeout")
    void timeoutTest() {
        assertThat(unit.getTimeout()).isEqualTo(timeout);
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
