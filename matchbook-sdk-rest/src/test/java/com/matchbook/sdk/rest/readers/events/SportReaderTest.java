package com.matchbook.sdk.rest.readers.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.events.Sport;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SportReaderTest extends ResponseReaderTest<SportReader> {

    @Override
    protected SportReader newReader() {
        return new SportReader();
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, true);
        when(parser.getFieldName()).thenReturn("id", "name");
        when(parser.getLong()).thenReturn(15L);
        when(parser.getString()).thenReturn("Soccer");

        Sport sport = unit.readNextItem();

        assertThat(sport).isNotNull();
        assertThat(sport.getId()).isEqualTo(15L);
        assertThat(sport.getName()).isEqualTo("Soccer");
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
