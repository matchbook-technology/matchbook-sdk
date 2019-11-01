package com.matchbook.sdk.rest.readers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class ResponseReaderTest<T extends ResponseReader> extends BaseReaderTest<T> {

    @Test
    @DisplayName("Next item is null if already read")
    void readNextItemReadTest() {
        unit.readingItemStatus = BaseReader.ReadingItemsStatus.READ;

        assertThat(unit.readNextItem()).isNull();
    }

    @Test
    @DisplayName("Full response is null if already read")
    void readFullResponseReadTest() {
        unit.readingItemStatus = BaseReader.ReadingItemsStatus.READ;

        assertThat(unit.readFullResponse()).isNull();
    }

}
