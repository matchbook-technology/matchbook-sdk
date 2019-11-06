package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.matchbook.sdk.rest.dtos.FailableRestResponseTest;
import com.matchbook.sdk.rest.dtos.RestResponseTest;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OfferEditTest extends FailableRestResponseTest<OfferEdit> {

    @Override
    protected OfferEdit newResponse() {
        return new OfferEdit();
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
    @DisplayName("Set and get runner ID")
    void runnerIdTest() {
        Long runnerId = 395750978870010L;
        unit.setRunnerId(runnerId);
        assertThat(unit.getRunnerId()).isEqualTo(runnerId);
    }

    @Test
    @DisplayName("Set and get status")
    void statusTest() {
        OfferEditStatus status = OfferEditStatus.APPLIED;
        unit.setStatus(status);
        assertThat(unit.getStatus()).isEqualTo(status);
    }

    @Test
    @DisplayName("Set and get odds type")
    void oddsTypeTest() {
        OddsType oddsType = OddsType.DECIMAL;
        unit.setOddsType(oddsType);
        assertThat(unit.getOddsType()).isEqualTo(oddsType);
    }

    @Test
    @DisplayName("Set and get odds before")
    void oddsBeforeTest() {
        Double oddsBefore = 1.5d;
        unit.setOddsBefore(oddsBefore);
        assertThat(unit.getOddsBefore()).isEqualTo(oddsBefore);
    }

    @Test
    @DisplayName("Set and get odds after")
    void oddsAfterTest() {
        Double oddsAfter = 1.65d;
        unit.setOddsAfter(oddsAfter);
        assertThat(unit.getOddsAfter()).isEqualTo(oddsAfter);
    }

    @Test
    @DisplayName("Set and get stake before")
    void stakeBeforeTest() {
        BigDecimal stakeBefore = new BigDecimal("130");
        unit.setStakeBefore(stakeBefore);
        assertThat(unit.getStakeBefore()).isEqualByComparingTo(stakeBefore);
    }

    @Test
    @DisplayName("Set and get stake after")
    void stakeAfterTest() {
        BigDecimal stakeAfter = new BigDecimal("140.50");
        unit.setStakeAfter(stakeAfter);
        assertThat(unit.getStakeAfter()).isEqualByComparingTo(stakeAfter);
    }

    @Test
    @DisplayName("Set and get delay")
    void delayTest() {
        Double delay = 6d;
        unit.setDelay(delay);
        assertThat(unit.getDelay()).isEqualTo(delay);
    }

    @Test
    @DisplayName("Set and get edit time")
    void editTimeTest() {
        Instant editTime = Instant.now();
        unit.setEditTime(editTime);
        assertThat(unit.getEditTime()).isCloseTo(editTime, within(1L, ChronoUnit.SECONDS));
    }

    @Test
    @DisplayName("Set and get errors")
    void errorsTest() {
        Errors errors = mock(Errors.class);
        unit.setErrors(errors);
        assertThat(unit.getErrors()).isEqualTo(errors);
    }

}
