package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import com.matchbook.sdk.rest.dtos.RestResponseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest extends RestResponseTest<Position> {

    @Override
    protected Position newResponse() {
        return new Position();
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
    @DisplayName("Set and get potential profit")
    void potentialProfitTest() {
        BigDecimal potentialProfit = new BigDecimal("195");
        unit.setPotentialProfit(potentialProfit);
        assertThat(unit.getPotentialProfit()).isEqualByComparingTo(potentialProfit);
    }

    @Test
    @DisplayName("Set and get potential loss")
    void potentialLossTest() {
        BigDecimal potentialLoss = new BigDecimal("130");
        unit.setPotentialLoss(potentialLoss);
        assertThat(unit.getPotentialLoss()).isEqualByComparingTo(potentialLoss);
    }

}
