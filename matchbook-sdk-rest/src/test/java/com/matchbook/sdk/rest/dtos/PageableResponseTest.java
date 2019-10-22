package com.matchbook.sdk.rest.dtos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class PageableResponseTest<T extends PageableResponse<R>, R> {

    protected T unit;

    protected abstract T newPageableResponse();

    protected abstract R mockItem();

    @BeforeEach
    protected void setUp() {
        unit = newPageableResponse();
    }

    @Test
    @DisplayName("Set and get total")
    void totalTest() {
        int total = 42;
        unit.setTotal(total);
        assertThat(unit.getTotal()).isEqualTo(total);
    }

    @Test
    @DisplayName("Set and get offset")
    void offsetTest() {
        int offset = 20;
        unit.setOffset(offset);
        assertThat(unit.getOffset()).isEqualTo(offset);
    }

    @Test
    @DisplayName("Set and get per page")
    void perPageTest() {
        int perPage = 50;
        unit.setPerPage(perPage);
        assertThat(unit.getPerPage()).isEqualTo(perPage);
    }

    @Test
    @DisplayName("Set and get items")
    void itemsTest() {
        R item = mockItem();
        unit.setItems(Collections.singletonList(item));
        assertThat(unit.getItems()).containsOnly(item);
    }

}
