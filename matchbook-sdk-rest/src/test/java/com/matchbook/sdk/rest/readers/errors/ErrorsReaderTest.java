package com.matchbook.sdk.rest.readers.errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ErrorsReaderTest extends ResponseReaderTest<ErrorsReader> {

    @Mock
    private ErrorReader errorReader;

    @Override
    protected ErrorsReader newReader() {
        return new ErrorsReader(errorReader);
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemWithContentTest() {
        when(parser.isEndOfObject()).thenReturn(false, true);
        when(parser.getFieldName()).thenReturn("errors");

        when(parser.isEndOfArray()).thenReturn(false, false, true);
        Error error = mock(Error.class);
        when(errorReader.readFullResponse()).thenReturn(error);

        Errors errors = unit.readNextItem();

        assertThat(errors).isNotNull();
        assertThat(errors.getErrors()).containsOnly(error);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
