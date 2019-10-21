package com.matchbook.sdk.rest.dtos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class PageableRequestTest<T extends PageableRequest> {

    private int offset;
    private int perPage;

    protected T unit;

    protected abstract T newPageableRequest(int offset, int perPage);

    @BeforeEach
    void setUp() {
        offset = 5;
        perPage = 100;
        unit = newPageableRequest(offset, perPage);
    }

    @Test
    @DisplayName("Check page offset")
    void offsetTest() {
        int actualOffset = unit.getOffset();
        assertThat(actualOffset).isEqualTo(offset);
    }

    @Test
    @DisplayName("Check items per page")
    void perPageTest() {
        int actualPerPage = unit.getPerPage();
        assertThat(actualPerPage).isEqualTo(perPage);
    }

    @Test
    @DisplayName("Query parameters contain pagination values")
    void parametersTest() {
        Map<String, String> parameters = unit.parameters();
        assertThat(parameters).extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(
                        tuple("offset", String.valueOf(offset)),
                        tuple("per-page", String.valueOf(perPage))
                );
    }

}
