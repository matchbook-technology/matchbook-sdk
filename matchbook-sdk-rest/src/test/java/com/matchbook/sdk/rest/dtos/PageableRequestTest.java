package com.matchbook.sdk.rest.dtos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class PageableRequestTest<T extends PageableRequest> extends RestRequestTest<T> {

    private int offset;
    private int perPage;

    protected abstract T newPageableRequest(int offset, int perPage);

    @Override
    @BeforeEach
    protected void setUp() {
        offset = 5;
        perPage = 100;

        super.setUp();
    }

    @Override
    protected T newRequest() {
        return newPageableRequest(offset, perPage);
    }

    @Test
    @DisplayName("Check page offset")
    void offsetTest() {
        assertThat(unit.getOffset()).isEqualTo(offset);
    }

    @Test
    @DisplayName("Check items per page")
    void perPageTest() {
        assertThat(unit.getPerPage()).isEqualTo(perPage);
    }

    @Test
    @DisplayName("Query parameters contain pagination params")
    void pageParametersTest() {
        Map<String, String> parameters = unit.parameters();
        assertThat(parameters)
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(
                        tuple("offset", String.valueOf(offset)),
                        tuple("per-page", String.valueOf(perPage))
                );
    }

}
