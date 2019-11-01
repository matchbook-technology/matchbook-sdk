package com.matchbook.sdk.rest.readers.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.offers.MatchedBet;
import com.matchbook.sdk.rest.dtos.offers.MatchedBetStatus;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchedBetReaderTest extends ResponseReaderTest<MatchedBetReader> {

    @Override
    protected MatchedBetReader newReader() {
        return new MatchedBetReader();
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false, false,
                false, false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("id", "offer-id", "status", "odds-type", "odds", "decimal-odds",
                "currency", "stake", "potential-profit", "potential-liability", "commission", "created-at");
        when(parser.getLong()).thenReturn(425098661640113L, 425096769380013L);
        when(parser.getEnum(MatchedBetStatus.class)).thenReturn(MatchedBetStatus.PAID);
        when(parser.getEnum(OddsType.class)).thenReturn(OddsType.DECIMAL);
        when(parser.getEnum(Currency.class)).thenReturn(Currency.USD);
        when(parser.getDouble()).thenReturn(1.5d, 1.5d);
        when(parser.getDecimal()).thenReturn(new BigDecimal("130"), new BigDecimal("195"),
                new BigDecimal("130"), new BigDecimal("1.48"));
        Instant createdAt = Instant.now();
        when(parser.getInstant()).thenReturn(createdAt);

        MatchedBet matchedBet = unit.readNextItem();

        assertThat(matchedBet).isNotNull();
        assertThat(matchedBet.getId()).isEqualTo(425098661640113L);
        assertThat(matchedBet.getOfferId()).isEqualTo(425096769380013L);
        assertThat(matchedBet.getStatus()).isEqualTo(MatchedBetStatus.PAID);
        assertThat(matchedBet.getCurrency()).isEqualTo(Currency.USD);
        assertThat(matchedBet.getOddsType()).isEqualTo(OddsType.DECIMAL);
        assertThat(matchedBet.getOdds()).isEqualTo(1.5d);
        assertThat(matchedBet.getDecimalOdds()).isEqualTo(1.5d);
        assertThat(matchedBet.getStake()).isEqualByComparingTo(new BigDecimal("130"));
        assertThat(matchedBet.getPotentialProfit()).isEqualByComparingTo(new BigDecimal("195"));
        assertThat(matchedBet.getPotentialLiability()).isEqualByComparingTo(new BigDecimal("130"));
        assertThat(matchedBet.getCommission()).isEqualByComparingTo(new BigDecimal("1.48"));
        assertThat(matchedBet.getCreatedAt()).isCloseTo(createdAt, within(1L, ChronoUnit.SECONDS));
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
