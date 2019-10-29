package com.matchbook.sdk.rest.dtos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class FailableRestResponseTest<T extends FailableRestResponse> extends RestResponseTest<T> {

    @Test
    @DisplayName("Check if response failed")
    void hasFailedTest() {
        assertThat(unit.hasFailed()).isFalse();
    }

}
