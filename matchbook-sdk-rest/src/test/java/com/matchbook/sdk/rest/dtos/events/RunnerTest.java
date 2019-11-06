package com.matchbook.sdk.rest.dtos.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.RestResponseTest;
import com.matchbook.sdk.rest.dtos.prices.Price;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunnerTest extends RestResponseTest<Runner> {

    @Override
    protected Runner newResponse() {
        return new Runner();
    }

    @Test
    @DisplayName("Set and get ID")
    void idTest() {
        Long id = 395750978870010L;
        unit.setId(id);
        assertThat(unit.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Set and get event ID")
    void eventIdTest() {
        Long eventId = 395729780570010L;
        unit.setEventId(eventId);
        assertThat(unit.getEventId()).isEqualTo(eventId);
    }

    @Test
    @DisplayName("Set and get market ID")
    void marketIdTest() {
        Long marketId = 395729860260010L;
        unit.setMarketId(marketId);
        assertThat(unit.getMarketId()).isEqualTo(marketId);
    }

    @Test
    @DisplayName("Set and get event participant ID")
    void eventParticipantIdTest() {
        Long eventParticipantId = 0L;
        unit.setEventParticipantId(eventParticipantId);
        assertThat(unit.getEventParticipantId()).isEqualTo(eventParticipantId);
    }

    @Test
    @DisplayName("Set and get name")
    void nameTest() {
        String name = "OVER 1.5";
        unit.setName(name);
        assertThat(unit.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("Set and get status")
    void runnerStatusTest() {
        RunnerStatus status = RunnerStatus.CLOSED;
        unit.setStatus(status);
        assertThat(unit.getStatus()).isEqualTo(status);
    }

    @Test
    @DisplayName("Set and get withdrawn")
    void withdrawnTest() {
        unit.setWithdrawn(false);
        assertThat(unit.isWithdrawn()).isFalse();
    }

    @Test
    @DisplayName("Set and get handicap value")
    void handicapTest() {
        Double handicap = 1.5d;
        unit.setHandicap(handicap);
        assertThat(unit.getHandicap()).isEqualTo(handicap);
    }

    @Test
    @DisplayName("Set and get volume")
    void volumeTest() {
        Double volume = 317660.08d;
        unit.setVolume(volume);
        assertThat(unit.getVolume()).isEqualTo(volume);
    }

    @Test
    @DisplayName("Set and get prices")
    void pricesTest() {
        Price prices = mock(Price.class);
        unit.setPrices(Collections.singletonList(prices));
        assertThat(unit.getPrices()).containsOnly(prices);
    }

}
