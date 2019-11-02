package com.matchbook.sdk.rest.readers.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.events.Market;
import com.matchbook.sdk.rest.dtos.events.MarketStatus;
import com.matchbook.sdk.rest.dtos.events.MarketType;
import com.matchbook.sdk.rest.dtos.events.Runner;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class MarketReaderTest extends ResponseReaderTest<MarketReader> {

    @Mock
    private RunnerReader runnerReader;

    @Override
    protected MarketReader newReader() {
        return new MarketReader(runnerReader);
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false, false, false,
                false, false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("id", "event-id", "name", "market-type", "status", "in-running-flag",
                "allow-live-betting", "handicap", "volume", "winners", "back-overround", "lay-overround", "runners");
        when(parser.getLong()).thenReturn(395729860260010L, 395729780570010L);
        when(parser.getString()).thenReturn("Over/Under 1.5");
        when(parser.getEnum(MarketType.class)).thenReturn(MarketType.TOTAL);
        when(parser.getEnum(MarketStatus.class)).thenReturn(MarketStatus.GRADED);
        when(parser.getBoolean()).thenReturn(false, true);
        when(parser.getDouble()).thenReturn(1.5d, 317660.08d, 100.51d, 99.17d);
        when(parser.getInteger()).thenReturn(1);

        when(parser.isEndOfArray()).thenReturn(false, true);
        Runner runner = mock(Runner.class);
        when(runnerReader.readFullResponse()).thenReturn(runner);

        Market market = unit.readNextItem();

        assertThat(market).isNotNull();
        assertThat(market.getId()).isEqualTo(395729860260010L);
        assertThat(market.getEventId()).isEqualTo(395729780570010L);
        assertThat(market.getName()).isEqualTo("Over/Under 1.5");
        assertThat(market.getMarketType()).isEqualTo(MarketType.TOTAL);
        assertThat(market.getStatus()).isEqualTo(MarketStatus.GRADED);
        assertThat(market.isInRunning()).isFalse();
        assertThat(market.isAllowLiveBetting()).isTrue();
        assertThat(market.getHandicap()).isEqualTo(1.5d);
        assertThat(market.getVolume()).isEqualTo(317660.08d);
        assertThat(market.getWinners()).isEqualTo(1);
        assertThat(market.getBackOverround()).isEqualTo(100.51d);
        assertThat(market.getLayOverround()).isEqualTo(99.17d);
        assertThat(market.getRunners()).containsOnly(runner);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
