package com.matchbook.sdk.rest.readers.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.offers.Position;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionReaderTest extends ResponseReaderTest<PositionReader> {

    @Override
    protected PositionReader newReader() {
        return new PositionReader();
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("event-id", "market-id", "runner-id", "potential-profit", "potential-loss");
        when(parser.getLong()).thenReturn(395729780570010L, 395729860260010L, 395750978870010L);
        when(parser.getDecimal()).thenReturn(new BigDecimal("195"), new BigDecimal("130.47"));

        Position position = unit.readNextItem();

        assertThat(position).isNotNull();
        assertThat(position.getEventId()).isEqualTo(395729780570010L);
        assertThat(position.getMarketId()).isEqualTo(395729860260010L);
        assertThat(position.getRunnerId()).isEqualTo(395750978870010L);
        assertThat(position.getPotentialProfit()).isEqualByComparingTo(new BigDecimal("195"));
        assertThat(position.getPotentialLoss()).isEqualByComparingTo(new BigDecimal("130.47"));
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
