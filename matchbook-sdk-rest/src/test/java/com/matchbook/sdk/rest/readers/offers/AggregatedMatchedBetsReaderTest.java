package com.matchbook.sdk.rest.readers.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBet;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBetsResponse;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.readers.PageableResponseReaderTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class AggregatedMatchedBetsReaderTest
        extends PageableResponseReaderTest<AggregatedMatchedBetsReader, AggregatedMatchedBet, AggregatedMatchedBetsResponse> {

    @Mock
    private AggregatedMatchedBetReader aggregatedMatchedBetReader;

    @Mock
    private AggregatedMatchedBet aggregatedMatchedBet;

    @Override
    protected AggregatedMatchedBetsReader newReader() {
        return new AggregatedMatchedBetsReader(aggregatedMatchedBetReader);
    }

    @Override
    protected AggregatedMatchedBetReader getItemsReader() {
        return aggregatedMatchedBetReader;
    }

    @Override
    protected AggregatedMatchedBet getItem() {
        return aggregatedMatchedBet;
    }

    @Override
    protected String getItemsFieldName() {
        return "matched-bets";
    }

    @Test
    @Override
    @DisplayName("Read full response")
    protected void readFullResponseTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("total", "offset", "per-page",
                "currency", "exchange-type", "odds-type", "matched-bets");
        when(parser.getInteger()).thenReturn(42, 0, 20);
        when(parser.getEnum(Currency.class)).thenReturn(Currency.HKD);
        when(parser.getEnum(ExchangeType.class)).thenReturn(ExchangeType.BINARY);
        when(parser.getEnum(OddsType.class)).thenReturn(OddsType.PERCENTAGE);

        when(parser.isEndOfArray()).thenReturn(false, true);
        when(aggregatedMatchedBetReader.readFullResponse()).thenReturn(aggregatedMatchedBet);

        AggregatedMatchedBetsResponse aggregatedMatchedBets = unit.readFullResponse();

        assertThat(aggregatedMatchedBets).isNotNull();
        assertThat(aggregatedMatchedBets.getTotal()).isEqualTo(42);
        assertThat(aggregatedMatchedBets.getOffset()).isEqualTo(0);
        assertThat(aggregatedMatchedBets.getPerPage()).isEqualTo(20);
        assertThat(aggregatedMatchedBets.getCurrency()).isEqualTo(Currency.HKD);
        assertThat(aggregatedMatchedBets.getExchangeType()).isEqualTo(ExchangeType.BINARY);
        assertThat(aggregatedMatchedBets.getOddsType()).isEqualTo(OddsType.PERCENTAGE);
        assertThat(aggregatedMatchedBets.getItems()).containsOnly(aggregatedMatchedBet);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
