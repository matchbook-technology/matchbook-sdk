package com.matchbook.sdk.rest.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountRequestTest extends RestRequestTest<AccountRequest> {

    @Override
    protected AccountRequest newRequest() {
        return new AccountRequest.Builder().build();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("account");
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).isEmpty();
    }

}
