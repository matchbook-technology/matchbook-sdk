package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.prices.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AggregatedMatchedBetTest extends BaseMatchedBetTest<AggregatedMatchedBet> {

    @Override
    protected AggregatedMatchedBet newResponse() {
        return new AggregatedMatchedBet();
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
    @DisplayName("Set and get runner ID")
    void runnerIdTest() {
        Long runnerId = 395750978870010L;
        unit.setRunnerId(runnerId);
        assertThat(unit.getRunnerId()).isEqualTo(runnerId);
    }

    @Test
    @DisplayName("Set and get side")
    void sideTest() {
        Side side = Side.BACK;
        unit.setSide(side);
        assertThat(unit.getSide()).isEqualTo(side);
    }

    @Test
    @DisplayName("Set and get event name")
    void eventNameTest() {
        String eventName = "CSKA Moscow vs Bayer 04 Leverkusen";
        unit.setEventName(eventName);
        assertThat(unit.getEventName()).isEqualTo(eventName);
    }

    @Test
    @DisplayName("Set and get market name")
    void marketNameTest() {
        String marketName = "Over/Under 1.5";
        unit.setMarketName(marketName);
        assertThat(unit.getMarketName()).isEqualTo(marketName);
    }

    @Test
    @DisplayName("Set and get runner name")
    void runnerNameTest() {
        String runnerName = "OVER 1.5";
        unit.setRunnerName(runnerName);
        assertThat(unit.getRunnerName()).isEqualTo(runnerName);
    }

}
