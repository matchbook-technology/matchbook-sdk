package com.matchbook.sdk.rest.dtos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.dtos.errors.Errors;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class PartiallyFailableResponseTest<T extends PartiallyFailableResponse<R>, R extends FailableRestResponse>
        extends PageableResponseTest<T, R> {

    @Test
    @DisplayName("Response does not fail when empty")
    void hasFailedEmptyTest() {
        unit.setItems(Collections.emptyList());
        assertThat(unit.hasFailed()).isFalse();
    }

    @Test
    @DisplayName("Response does not fail when all items do not fail")
    void hasFailedNoFailedItemsTest() {
        R notFailedItem = mockItem();
        when(notFailedItem.hasFailed()).thenReturn(false);

        unit.setItems(Collections.singletonList(notFailedItem));
        assertThat(unit.hasFailed()).isFalse();
    }

    @Test
    @DisplayName("Response fails when just one item fails")
    void hasFailedFailedItemsTest() {
        R failedItem = mockItem();
        when(failedItem.hasFailed()).thenReturn(true);
        R notFailedItem = mockItem();
        when(notFailedItem.hasFailed()).thenReturn(false);

        unit.setItems(Arrays.asList(failedItem, notFailedItem));
        assertThat(unit.hasFailed()).isTrue();
    }

    @Test
    @DisplayName("Errors are absent when response does not fail")
    void getErrorsNotFailedTest() {
        R notFailedItem = mockItem();
        when(notFailedItem.hasFailed()).thenReturn(false);

        unit.setItems(Collections.singletonList(notFailedItem));

        Errors result = unit.getErrors();
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Errors are present when response fail")
    void getErrorsFailedTest() {
        R failedItem = mockItem();
        when(failedItem.hasFailed()).thenReturn(true);

        Errors errors = mock(Errors.class);
        Error error = mock(Error.class);
        when(errors.getErrors()).thenReturn(Collections.singletonList(error));
        when(failedItem.getErrors()).thenReturn(errors);

        unit.setItems(Collections.singletonList(failedItem));

        Errors result = unit.getErrors();
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotEmpty();
        assertThat(result.getErrors()).containsOnly(error);
    }

}
