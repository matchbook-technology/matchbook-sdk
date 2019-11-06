package com.matchbook.sdk.rest.dtos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class RestResponseTest<T extends RestResponse> {

    protected T unit;

    protected abstract T newResponse();

    @BeforeEach
    protected void setUp() {
        unit = newResponse();
    }

    @Test
    @DisplayName("Object description")
    void toStringTest() {
        String objectDescription = unit.toString();
        assertThat(objectDescription).startsWith(unit.getClass().getSimpleName());
    }

}
