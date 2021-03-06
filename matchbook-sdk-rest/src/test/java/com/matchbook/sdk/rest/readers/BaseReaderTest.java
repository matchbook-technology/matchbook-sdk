package com.matchbook.sdk.rest.readers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.configs.Parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class BaseReaderTest<X extends BaseReader> {

    @Mock
    protected Parser parser;

    protected X unit;

    protected abstract X newReader();

    @BeforeEach
    void setUp() {
        unit = newReader();

        when(parser.hasCurrentToken()).thenReturn(true);
        unit.init(parser);
    }

    @Test
    @DisplayName("Status not read if init with not empty parser")
    void initNotEmptyReaderTest() {
        when(parser.hasCurrentToken()).thenReturn(true);
        unit.init(parser);

        assertThat(unit.readingItemStatus).isEqualTo(BaseReader.ReadingItemsStatus.NOT_READ);
    }

    @Test
    @DisplayName("Status read if init with empty parser")
    void initEmptyReaderTest() {
        when(parser.hasCurrentToken()).thenReturn(false);
        unit.init(parser);

        assertThat(unit.readingItemStatus).isEqualTo(BaseReader.ReadingItemsStatus.READ);
    }

    @Test
    @DisplayName("Has more items if not fully read")
    void hasMoreItemsNotReadTest() {
        unit.readingItemStatus = BaseReader.ReadingItemsStatus.NOT_READ;

        assertThat(unit.hasMoreItems()).isTrue();
    }

    @Test
    @DisplayName("Has no more items if already read")
    void hasMoreItemsAlreadyReadTest() {
        unit.readingItemStatus = BaseReader.ReadingItemsStatus.READ;

        assertThat(unit.hasMoreItems()).isFalse();
    }

}
