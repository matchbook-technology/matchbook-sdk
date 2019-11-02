package com.matchbook.sdk.rest.readers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.PageableResponse;
import com.matchbook.sdk.rest.dtos.RestResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class PageableResponseReaderTest<X extends PageableResponseReader<T, R>, T extends RestResponse,
        R extends PageableResponse<T>> extends BaseReaderTest<X> {

    protected abstract ResponseReader<T> getItemsReader();

    protected abstract T getItem();

    protected abstract String getItemsFieldName();

    @Test
    @DisplayName("Next item is null if already read")
    void readNextItemReadTest() {
        unit.readingItemStatus = BaseReader.ReadingItemsStatus.READ;

        assertThat(unit.readNextItem()).isNull();
    }

    @Test
    @DisplayName("Read next item and keep reading")
    void readNextItemTest() {
        unit.readingItemStatus = BaseReader.ReadingItemsStatus.NOT_READ;
        when(parser.isEndOfObject()).thenReturn(false);
        when(parser.isEndOfArray()).thenReturn(false);
        when(parser.getFieldName()).thenReturn(null).thenReturn(getItemsFieldName());

        ResponseReader<T> itemsReader = getItemsReader();
        T item = getItem();
        when(itemsReader.readFullResponse()).thenReturn(item);

        T readItem = unit.readNextItem();

        assertThat(readItem).isEqualTo(item);
        assertThat(unit.hasMoreItems()).isTrue();
    }

    @Test
    @DisplayName("Read last item")
    void readLastItemTest() {
        unit.readingItemStatus = BaseReader.ReadingItemsStatus.READING;
        ResponseReader<T> itemsReader = getItemsReader();
        T item = getItem();
        when(itemsReader.readFullResponse()).thenReturn(item);
        when(parser.isEndOfArray()).thenReturn(true);

        T readItem = unit.readNextItem();

        assertThat(readItem).isEqualTo(item);
        assertThat(unit.hasMoreItems()).isFalse();
    }

    @Test
    @DisplayName("Read full response")
    void readFullResponseTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("total", "offset", "per-page", getItemsFieldName());
        when(parser.getInteger()).thenReturn(42, 0, 20);

        when(parser.isEndOfArray()).thenReturn(false, true);
        ResponseReader<T> itemsReader = getItemsReader();
        T item = getItem();
        when(itemsReader.readFullResponse()).thenReturn(item);

        R response = unit.readFullResponse();

        assertThat(response).isNotNull();
        assertThat(response.getTotal()).isEqualTo(42);
        assertThat(response.getOffset()).isEqualTo(0);
        assertThat(response.getPerPage()).isEqualTo(20);
        assertThat(response.getItems()).containsOnly(item);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
