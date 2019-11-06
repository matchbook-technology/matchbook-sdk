package com.matchbook.sdk.rest.readers.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.user.Account;
import com.matchbook.sdk.rest.dtos.user.Login;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class LoginReaderTest extends ResponseReaderTest<LoginReader> {

    @Mock
    private AccountReader accountReader;

    @Override
    protected LoginReader newReader() {
        return new LoginReader(accountReader);
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, true);
        when(parser.getFieldName()).thenReturn("session-token", "user-id", "account");
        when(parser.getString()).thenReturn("000000_8beb227cee5689");
        when(parser.getLong()).thenReturn(4242L);

        Account account = mock(Account.class);
        when(accountReader.readFullResponse()).thenReturn(account);

        Login login = unit.readNextItem();

        assertThat(login).isNotNull();
        assertThat(login.getSessionToken()).isEqualTo("000000_8beb227cee5689");
        assertThat(login.getUserId()).isEqualTo(4242L);
        assertThat(login.getAccount()).isEqualTo(account);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
