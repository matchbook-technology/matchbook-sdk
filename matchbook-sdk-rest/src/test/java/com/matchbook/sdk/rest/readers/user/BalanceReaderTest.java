package com.matchbook.sdk.rest.readers.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.user.Balance;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BalanceReaderTest extends ResponseReaderTest<BalanceReader> {

    @Override
    protected BalanceReader newReader() {
        return new BalanceReader();
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("id", "balance", "exposure", "free-funds", "commission-reserve");
        when(parser.getLong()).thenReturn(4242L);
        when(parser.getDecimal()).thenReturn(new BigDecimal("1502.39"), new BigDecimal("207.19"),
                new BigDecimal("1295.20"), BigDecimal.ZERO);

        Balance balance = unit.readNextItem();

        assertThat(balance).isNotNull();
        assertThat(balance.getId()).isEqualTo(4242L);
        assertThat(balance.getBalance()).isEqualByComparingTo(new BigDecimal("1502.39"));
        assertThat(balance.getExposure()).isEqualByComparingTo(new BigDecimal("207.19"));
        assertThat(balance.getFreeFunds()).isEqualByComparingTo(new BigDecimal("1295.20"));
        assertThat(balance.getCommissionReserve()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
