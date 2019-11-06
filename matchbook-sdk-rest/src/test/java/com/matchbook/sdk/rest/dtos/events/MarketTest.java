package com.matchbook.sdk.rest.dtos.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.RestResponseTest;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MarketTest extends RestResponseTest<Market> {

    @Override
    protected Market newResponse() {
        return new Market();
    }

    @Test
    @DisplayName("Set and get ID")
    void idTest() {
        Long id = 395729860260010L;
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
    @DisplayName("Set and get name")
    void nameTest() {
        String name = "Over/Under 1.5";
        unit.setName(name);
        assertThat(unit.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("Set and get status")
    void marketStatusTest() {
        MarketStatus status = MarketStatus.OPEN;
        unit.setStatus(status);
        assertThat(unit.getStatus()).isEqualTo(status);
    }

    @Test
    @DisplayName("Set and get type")
    void marketTypeTest() {
        MarketType marketType = MarketType.TOTAL;
        unit.setMarketType(marketType);
        assertThat(unit.getMarketType()).isEqualTo(marketType);
    }

    @Test
    @DisplayName("Set and get in running")
    void inRunningTest() {
        unit.setInRunning(true);
        assertThat(unit.isInRunning()).isTrue();
    }

    @Test
    @DisplayName("Set and get allow live betting")
    void allowLiveBettingTest() {
        unit.setAllowLiveBetting(true);
        assertThat(unit.isAllowLiveBetting()).isTrue();
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
        Double volume = 1100728.67d;
        unit.setVolume(volume);
        assertThat(unit.getVolume()).isEqualTo(volume);
    }

    @Test
    @DisplayName("Set and get back overround")
    void backOverroundTest() {
        Double overround = 100.51d;
        unit.setBackOverround(overround);
        assertThat(unit.getBackOverround()).isEqualTo(overround);
    }

    @Test
    @DisplayName("Set and get lay overround")
    void layOverroundTest() {
        Double overround = 99.17d;
        unit.setLayOverround(overround);
        assertThat(unit.getLayOverround()).isEqualTo(overround);
    }

    @Test
    @DisplayName("Set and get winners")
    void winnersTest() {
        Integer winners = 1;
        unit.setWinners(winners);
        assertThat(unit.getWinners()).isEqualTo(winners);
    }

    @Test
    @DisplayName("Set and get runners")
    void runnersTest() {
        Runner runner = mock(Runner.class);
        unit.setRunners(Collections.singletonList(runner));
        assertThat(unit.getRunners()).containsOnly(runner);
    }

}
