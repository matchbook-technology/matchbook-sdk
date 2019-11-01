package com.matchbook.sdk.rest.readers.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.events.Runner;
import com.matchbook.sdk.rest.dtos.events.RunnerStatus;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;
import com.matchbook.sdk.rest.readers.prices.PriceReader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class RunnerReaderTest extends ResponseReaderTest<RunnerReader> {

    @Mock
    private PriceReader priceReader;

    @Override
    protected RunnerReader newReader() {
        return new RunnerReader(priceReader);
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false,
                false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("id", "event-id", "market-id", "event-participant-id",
                "name", "status", "withdrawn", "handicap", "volume", "prices");
        when(parser.getLong()).thenReturn(395750978870010L, 395729780570010L, 395729860260010L, 0L);
        when(parser.getString()).thenReturn("OVER 1.5");
        when(parser.getEnum(RunnerStatus.class)).thenReturn(RunnerStatus.PAID);
        when(parser.getBoolean()).thenReturn(false);
        when(parser.getDouble()).thenReturn(1.5d, 317660.08d);

        when(parser.isEndOfArray()).thenReturn(false, false, true);
        Price price = mock(Price.class);
        when(priceReader.readFullResponse()).thenReturn(price);

        Runner runner = unit.readNextItem();

        assertThat(runner).isNotNull();
        assertThat(runner.getId()).isEqualTo(395750978870010L);
        assertThat(runner.getEventId()).isEqualTo(395729780570010L);
        assertThat(runner.getMarketId()).isEqualTo(395729860260010L);
        assertThat(runner.getEventParticipantId()).isEqualTo(0L);
        assertThat(runner.getName()).isEqualTo("OVER 1.5");
        assertThat(runner.getStatus()).isEqualTo(RunnerStatus.PAID);
        assertThat(runner.isWithdrawn()).isFalse();
        assertThat(runner.getHandicap()).isEqualTo(1.5d);
        assertThat(runner.getVolume()).isEqualTo(317660.08d);
        assertThat(runner.getPrices()).containsOnly(price);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
