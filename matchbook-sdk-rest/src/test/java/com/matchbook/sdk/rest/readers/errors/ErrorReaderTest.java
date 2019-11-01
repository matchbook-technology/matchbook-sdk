package com.matchbook.sdk.rest.readers.errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ErrorReaderTest extends ResponseReaderTest<ErrorReader> {

    @Override
    protected ErrorReader newReader() {
        return new ErrorReader();
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, true);
        when(parser.isEndOfArray()).thenReturn(false, true);
        when(parser.getFieldName()).thenReturn("field", "messages");
        when(parser.getString()).thenReturn("stake", "Stake must be at least 0.01 EUR");

        Error error = unit.readNextItem();

        assertThat(error).isNotNull();
        assertThat(error.getField()).isEqualTo("stake");
        assertThat(error.getMessages()).containsOnly("Stake must be at least 0.01 EUR");
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
