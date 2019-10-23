package com.matchbook.sdk.rest.dtos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class RestRequestTest<T extends RestRequest> {

    protected T unit;
    protected T emptyUnit;

    protected abstract T newRequest();

    protected abstract T newEmptyRequest();

    @BeforeEach
    protected void setUp() {
        unit = newRequest();
        emptyUnit = newEmptyRequest();
    }

    @Test
    @DisplayName("Object description")
    void toStringTest() {
        String objectDescription = unit.toString();
        assertThat(objectDescription).startsWith(unit.getClass().getSimpleName());
    }

}
