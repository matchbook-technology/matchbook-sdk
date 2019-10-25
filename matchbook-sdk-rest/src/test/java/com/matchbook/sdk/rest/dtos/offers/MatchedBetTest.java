package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchedBetTest extends BaseMatchedBetTest<MatchedBet> {

    @Override
    protected MatchedBet newResponse() {
        return new MatchedBet();
    }

    @Test
    @DisplayName("Set and get ID")
    void idTest() {
        Long id = 425098661640113L;
        unit.setId(id);
        assertThat(unit.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Set and get offer ID")
    void offerIdTest() {
        Long offerId = 425096769380013L;
        unit.setOfferId(offerId);
        assertThat(unit.getOfferId()).isEqualTo(offerId);
    }

    @Test
    @DisplayName("Set and get commission")
    void commissionTest() {
        BigDecimal commission = new BigDecimal("1.48");
        unit.setCommission(commission);
        assertThat(unit.getCommission()).isEqualByComparingTo(commission);
    }

    @Test
    @DisplayName("Set and get creation time")
    void startTest() {
        Instant createdAt = Instant.now();
        unit.setCreatedAt(createdAt);
        assertThat(unit.getCreatedAt()).isCloseTo(createdAt, within(1L, ChronoUnit.SECONDS));
    }

}
