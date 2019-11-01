package com.matchbook.sdk.rest.readers.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBet;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.Side;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AggregatedMatchedBetReaderTest extends ResponseReaderTest<AggregatedMatchedBetReader> {

    @Override
    protected AggregatedMatchedBetReader newReader() {
        return new AggregatedMatchedBetReader();
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false, false, false,
                false, false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("event-id", "market-id", "runner-id", "side", "event-name", "market-name",
                "runner-name", "odds-type", "odds", "decimal-odds", "stake", "potential-profit", "potential-liability");
        when(parser.getLong()).thenReturn(395729780570010L, 395729860260010L, 395750978870010L);
        when(parser.getEnum(Side.class)).thenReturn(Side.BACK);
        when(parser.getEnum(OddsType.class)).thenReturn(OddsType.DECIMAL);
        when(parser.getString()).thenReturn("CSKA Moscow vs Bayer 04 Leverkusen", "Over/Under 1.5", "OVER 1.5");
        when(parser.getDouble()).thenReturn(1.5d, 1.5d);
        when(parser.getDecimal()).thenReturn(new BigDecimal("130"), new BigDecimal("195"), new BigDecimal("130"));

        AggregatedMatchedBet aggregatedMatchedBet = unit.readNextItem();

        assertThat(aggregatedMatchedBet).isNotNull();
        assertThat(aggregatedMatchedBet.getEventId()).isEqualTo(395729780570010L);
        assertThat(aggregatedMatchedBet.getMarketId()).isEqualTo(395729860260010L);
        assertThat(aggregatedMatchedBet.getRunnerId()).isEqualTo(395750978870010L);
        assertThat(aggregatedMatchedBet.getEventName()).isEqualTo("CSKA Moscow vs Bayer 04 Leverkusen");
        assertThat(aggregatedMatchedBet.getMarketName()).isEqualTo("Over/Under 1.5");
        assertThat(aggregatedMatchedBet.getRunnerName()).isEqualTo("OVER 1.5");
        assertThat(aggregatedMatchedBet.getSide()).isEqualTo(Side.BACK);
        assertThat(aggregatedMatchedBet.getOddsType()).isEqualTo(OddsType.DECIMAL);
        assertThat(aggregatedMatchedBet.getOdds()).isEqualTo(1.5d);
        assertThat(aggregatedMatchedBet.getDecimalOdds()).isEqualTo(1.5d);
        assertThat(aggregatedMatchedBet.getStake()).isEqualByComparingTo(new BigDecimal("130"));
        assertThat(aggregatedMatchedBet.getPotentialProfit()).isEqualByComparingTo(new BigDecimal("195"));
        assertThat(aggregatedMatchedBet.getPotentialLiability()).isEqualByComparingTo(new BigDecimal("130"));
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
