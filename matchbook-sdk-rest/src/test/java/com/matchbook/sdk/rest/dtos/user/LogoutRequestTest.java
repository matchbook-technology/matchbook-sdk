package com.matchbook.sdk.rest.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogoutRequestTest extends RestRequestTest<LogoutRequest> {

    @Override
    protected LogoutRequest newRequest() {
        return new LogoutRequest.Builder().build();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        assertThat(unit.resourcePath()).isEmpty();
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).isEmpty();
    }

}
