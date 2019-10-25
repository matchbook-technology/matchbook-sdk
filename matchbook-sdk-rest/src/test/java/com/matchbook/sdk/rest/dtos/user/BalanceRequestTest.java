package com.matchbook.sdk.rest.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BalanceRequestTest extends RestRequestTest<BalanceRequest> {

    @Override
    protected BalanceRequest newRequest() {
        return new BalanceRequest.Builder().build();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("account/balance");
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).isEmpty();
    }

}
