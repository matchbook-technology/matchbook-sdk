package com.matchbook.sdk.rest.readers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class BaseReaderTest<T extends BaseReader> {

    @Mock
    protected Parser parser;

    protected T unit;

    protected abstract T newReader();

    @BeforeEach
    protected void setUp() {
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
    @DisplayName("Init with exception")
    void initErrorTest() {
        doThrow(MatchbookSDKParsingException.class).when(parser).hasCurrentToken();

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.init(parser));
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
