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
        assertThat(unit.getId()).isEqualTo(id)
                .isEqualTo(emptyUnit.getId());
    }

    @Test
    @DisplayName("Check new odds")
    void newOddsTest() {
        assertThat(unit.getNewOdds()).isEqualTo(newOdds)
                .isEqualTo(emptyUnit.getNewOdds());
    }

    @Test
    @DisplayName("Check current odds")
    void currentOddsTest() {
        assertThat(unit.getCurrentOdds()).isEqualTo(currentOdds);
        assertThat(emptyUnit.getCurrentOdds()).isNull();
    }

    @Test
    @DisplayName("Check new stake")
    void newStakeTest() {
        assertThat(unit.getNewStake()).isEqualByComparingTo(newStake)
                .isEqualByComparingTo(emptyUnit.getNewStake());
    }

    @Test
    @DisplayName("Check current stake")
    void currentStakeTest() {
        assertThat(unit.getCurrentStake()).isEqualByComparingTo(currentStake);
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
