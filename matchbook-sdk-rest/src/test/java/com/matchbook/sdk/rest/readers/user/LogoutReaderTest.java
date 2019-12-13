package com.matchbook.sdk.rest.readers.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.user.Logout;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogoutReaderTest extends ResponseReaderTest<LogoutReader> {

    @Override
    protected LogoutReader newReader() {
        return new LogoutReader();
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, true);
        when(parser.getFieldName()).thenReturn("session-token", "user-id", "username");
        when(parser.getString()).thenReturn("000000_8beb227cee5689", "john_doe");
        when(parser.getLong()).thenReturn(4242L);

        Logout logout = unit.readNextItem();

        assertThat(logout).isNotNull();
        assertThat(logout.getSessionToken()).isEqualTo("000000_8beb227cee5689");
        assertThat(logout.getUsername()).isEqualTo("john_doe");
        assertThat(logout.getUserId()).isEqualTo(4242L);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
