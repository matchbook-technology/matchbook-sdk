package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OfferPutRequestTest extends RestRequestTest<OfferPutRequest> {

    private Long id;
    private Double currentOdds;
    private Double newOdds;
    private BigDecimal currentStake;
    private BigDecimal newStake;

    @Override
    @BeforeEach
    protected void setUp() {
        id = 395729860800010L;
        currentOdds = 7.2d;
        newOdds = 6.8d;
        currentStake = new BigDecimal("15");
        newStake = new BigDecimal("14.5");

        super.setUp();
    }

    @Override
    protected OfferPutRequest newRequest() {
        return new OfferPutRequest.Builder(id, newOdds, newStake)
                .currentOdds(currentOdds)
                .currentStake(currentStake)
                .build();
    }

    @Override
    protected OfferPutRequest newEmptyRequest() {
        return new OfferPutRequest.Builder(id, newOdds, newStake).build();
    }

    @Test
    @DisplayName("Check offer ID")
    void idTest() {
        Long actualId = unit.getId();
        assertThat(actualId).isEqualTo(emptyUnit.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Check new odds")
    void newOddsTest() {
        Double actualNewOdds = unit.getNewOdds();
        assertThat(actualNewOdds).isEqualTo(emptyUnit.getNewOdds()).isEqualTo(newOdds);
    }

    @Test
    @DisplayName("Check current odds")
    void currentOddsTest() {
        Double actualCurrentOdds = unit.getCurrentOdds();
        assertThat(actualCurrentOdds).isEqualTo(currentOdds);

        assertThat(emptyUnit.getCurrentOdds()).isNull();
    }

    @Test
    @DisplayName("Check new stake")
    void newStakeTest() {
        BigDecimal actualNewStake = unit.getNewStake();
        assertThat(actualNewStake).isEqualByComparingTo(emptyUnit.getNewStake()).isEqualByComparingTo(newStake);
    }

    @Test
    @DisplayName("Check current stake")
    void currentStakeTest() {
        BigDecimal actualCurrentStake = unit.getCurrentStake();
        assertThat(actualCurrentStake).isEqualByComparingTo(currentStake);

        assertThat(emptyUnit.getCurrentStake()).isNull();
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("v2/offers/" + id);
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).isEmpty();
    }

}
