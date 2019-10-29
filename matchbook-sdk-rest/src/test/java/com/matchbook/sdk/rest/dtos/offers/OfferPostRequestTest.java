package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;
import com.matchbook.sdk.rest.dtos.prices.Side;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OfferPostRequestTest extends RestRequestTest<OfferPostRequest> {

    private Long runnerId;
    private Side side;
    private Double odds;
    private BigDecimal stake;

    @Override
    @BeforeEach
    protected void setUp() {
        runnerId = 395729860800010L;
        side = Side.WIN;
        odds = 7.2d;
        stake = new BigDecimal("15");

        super.setUp();
    }

    @Override
    protected OfferPostRequest newRequest() {
        return new OfferPostRequest.Builder(runnerId, side, odds, stake)
                .keepInPlay(true)
                .build();
    }

    @Override
    protected OfferPostRequest newEmptyRequest() {
        return new OfferPostRequest.Builder(runnerId, side, odds, stake).build();
    }

    @Test
    @DisplayName("Check runner ID")
    void runnerIdTest() {
        Long actualRunnerId = unit.getRunnerId();
        assertThat(actualRunnerId).isEqualTo(emptyUnit.getRunnerId()).isEqualTo(runnerId);
    }

    @Test
    @DisplayName("Check side")
    void sideTest() {
        Side actualSide = unit.getSide();
        assertThat(actualSide).isEqualTo(emptyUnit.getSide()).isEqualTo(side);
    }

    @Test
    @DisplayName("Check odds")
    void oddsTest() {
        Double actualOdds = unit.getOdds();
        assertThat(actualOdds).isEqualTo(emptyUnit.getOdds()).isEqualTo(odds);
    }

    @Test
    @DisplayName("Check stake")
    void stakeTest() {
        BigDecimal actualStake = unit.getStake();
        assertThat(actualStake).isEqualByComparingTo(emptyUnit.getStake()).isEqualByComparingTo(stake);
    }

    @Test
    @DisplayName("Check keep in play")
    void keepInPlayTest() {
        assertThat(unit.isKeepInPlay()).isTrue();
        assertThat(emptyUnit.isKeepInPlay()).isFalse();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        assertThat(unit.resourcePath()).isEmpty();
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).isEmpty();
    }

}
