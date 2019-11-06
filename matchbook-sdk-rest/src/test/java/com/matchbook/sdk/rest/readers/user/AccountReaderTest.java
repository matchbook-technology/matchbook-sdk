package com.matchbook.sdk.rest.readers.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.offers.CommissionType;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.user.Account;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountReaderTest extends ResponseReaderTest<AccountReader> {

    @Override
    protected AccountReader newReader() {
        return new AccountReader();
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("id", "username", "exchange-type", "odds-type", "currency",
                "balance", "exposure", "free-funds", "commission-reserve", "commission-type",
                "name", "address", "roles", "user-security-question");
        when(parser.getLong()).thenReturn(4242L);
        when(parser.getString()).thenReturn("john_doe");
        when(parser.getEnum(ExchangeType.class)).thenReturn(ExchangeType.BACK_LAY);
        when(parser.getEnum(OddsType.class)).thenReturn(OddsType.MALAY);
        when(parser.getEnum(Currency.class)).thenReturn(Currency.AUD);
        when(parser.getEnum(CommissionType.class)).thenReturn(CommissionType.VOLUME);
        when(parser.getDecimal()).thenReturn(new BigDecimal("1502.39"), new BigDecimal("207.19"),
                new BigDecimal("1295.20"), BigDecimal.ZERO);

        Account account = unit.readNextItem();

        assertThat(account).isNotNull();
        assertThat(account.getId()).isEqualTo(4242L);
        assertThat(account.getUsername()).isEqualTo("john_doe");
        assertThat(account.getExchangeType()).isEqualTo(ExchangeType.BACK_LAY);
        assertThat(account.getOddsType()).isEqualTo(OddsType.MALAY);
        assertThat(account.getCurrency()).isEqualTo(Currency.AUD);
        assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal("1502.39"));
        assertThat(account.getExposure()).isEqualByComparingTo(new BigDecimal("207.19"));
        assertThat(account.getFreeFunds()).isEqualByComparingTo(new BigDecimal("1295.20"));
        assertThat(account.getCommissionReserve()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(account.getCommissionType()).isEqualTo(CommissionType.VOLUME);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
