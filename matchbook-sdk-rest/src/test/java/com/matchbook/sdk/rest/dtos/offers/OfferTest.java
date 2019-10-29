package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import com.matchbook.sdk.rest.dtos.FailableRestResponseTest;
import com.matchbook.sdk.rest.dtos.RestResponseTest;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.dtos.events.MarketType;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OfferTest extends FailableRestResponseTest<Offer> {

    @Override
    protected Offer newResponse() {
        return new Offer();
    }

    @Test
    @DisplayName("Set and get ID")
    void idTest() {
        Long id = 425096769380013L;
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
    @DisplayName("Set and get runner ID")
    void runnerIdTest() {
        Long runnerId = 395750978870010L;
        unit.setRunnerId(runnerId);
        assertThat(unit.getRunnerId()).isEqualTo(runnerId);
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

    @Test
    @DisplayName("Set and get market type")
    void marketTypeTest() {
        MarketType marketType = MarketType.TOTAL;
        unit.setMarketType(marketType);
        assertThat(unit.getMarketType()).isEqualTo(marketType);
    }

    @Test
    @DisplayName("Set and get status")
    void statusTest() {
        OfferStatus status = OfferStatus.OPEN;
        unit.setStatus(status);
        assertThat(unit.getStatus()).isEqualTo(status);
    }

    @Test
    @DisplayName("Set and get exchange type")
    void exchangeTypeTest() {
        ExchangeType exchangeType = ExchangeType.BINARY;
        unit.setExchangeType(exchangeType);
        assertThat(unit.getExchangeType()).isEqualTo(exchangeType);
    }

    @Test
    @DisplayName("Set and get side")
    void sideTest() {
        Side side = Side.LOSE;
        unit.setSide(side);
        assertThat(unit.getSide()).isEqualTo(side);
    }

    @Test
    @DisplayName("Set and get odds type")
    void oddsTypeTest() {
        OddsType oddsType = OddsType.US;
        unit.setOddsType(oddsType);
        assertThat(unit.getOddsType()).isEqualTo(oddsType);
    }

    @Test
    @DisplayName("Set and get currency")
    void currencyTest() {
        Currency currency = Currency.EUR;
        unit.setCurrency(currency);
        assertThat(unit.getCurrency()).isEqualTo(currency);
    }

    @Test
    @DisplayName("Set and get odds")
    void oddsTest() {
        Double odds = 320d;
        unit.setOdds(odds);
        assertThat(unit.getOdds()).isEqualTo(odds);
    }

    @Test
    @DisplayName("Set and get decimal odds")
    void decimalOddsTest() {
        Double decimalOdds = 4.2d;
        unit.setDecimalOdds(decimalOdds);
        assertThat(unit.getDecimalOdds()).isEqualTo(decimalOdds);
    }

    @Test
    @DisplayName("Set and get stake")
    void stakeTest() {
        BigDecimal stake = new BigDecimal("50");
        unit.setStake(stake);
        assertThat(unit.getStake()).isEqualByComparingTo(stake);
    }

    @Test
    @DisplayName("Set and get remaining")
    void remainingTest() {
        BigDecimal remaining = new BigDecimal("843.20");
        unit.setRemaining(remaining);
        assertThat(unit.getRemaining()).isEqualByComparingTo(remaining);
    }

    @Test
    @DisplayName("Set and get potential liability")
    void potentialLiabilityTest() {
        BigDecimal potentialLiability = new BigDecimal("160");
        unit.setPotentialLiability(potentialLiability);
        assertThat(unit.getPotentialLiability()).isEqualByComparingTo(potentialLiability);
    }

    @Test
    @DisplayName("Set and get remaining potential liability")
    void remainingPotentialLiabilityTest() {
        BigDecimal remainingPotentialLiability = new BigDecimal("2698.24");
        unit.setRemainingPotentialLiability(remainingPotentialLiability);
        assertThat(unit.getRemainingPotentialLiability()).isEqualByComparingTo(remainingPotentialLiability);
    }

    @Test
    @DisplayName("Set and get commission type")
    void commissionTypeTest() {
        CommissionType commissionType = CommissionType.VOLUME;
        unit.setCommissionType(commissionType);
        assertThat(unit.getCommissionType()).isEqualTo(commissionType);
    }

    @Test
    @DisplayName("Set and get originator commission rate")
    void originatorCommissionRateTest() {
        Double originatorCommissionRate = 0.02d;
        unit.setOriginatorCommissionRate(originatorCommissionRate);
        assertThat(unit.getOriginatorCommissionRate()).isEqualTo(originatorCommissionRate);
    }

    @Test
    @DisplayName("Set and get acceptor commission rate")
    void acceptorCommissionRateTest() {
        Double acceptorCommissionRate = 0.01d;
        unit.setAcceptorCommissionRate(acceptorCommissionRate);
        assertThat(unit.getAcceptorCommissionRate()).isEqualTo(acceptorCommissionRate);
    }

    @Test
    @DisplayName("Set and get commission reserve")
    void commissionReserveTest() {
        BigDecimal commissionReserve = BigDecimal.ZERO;
        unit.setCommissionReserve(commissionReserve);
        assertThat(unit.getCommissionReserve()).isEqualByComparingTo(commissionReserve);
    }

    @Test
    @DisplayName("Set and get in play")
    void inPlayTest() {
        unit.setInPlay(true);
        assertThat(unit.isInPlay()).isTrue();
    }

    @Test
    @DisplayName("Set and get keep in play")
    void keepInPlayTest() {
        unit.setKeepInPlay(true);
        assertThat(unit.isKeepInPlay()).isTrue();
    }

    @Test
    @DisplayName("Set and get creation time")
    void createdAtTest() {
        Instant createdAt = Instant.now();
        unit.setCreatedAt(createdAt);
        assertThat(unit.getCreatedAt()).isCloseTo(createdAt, within(1L, ChronoUnit.SECONDS));
    }

    @Test
    @DisplayName("Set and get matched bets")
    void matchedBetsTest() {
        MatchedBet matchedBet = mock(MatchedBet.class);
        unit.setMatchedBets(Collections.singletonList(matchedBet));
        assertThat(unit.getMatchedBets()).containsOnly(matchedBet);
    }

    @Test
    @DisplayName("Set and get offer edit")
    void offerEditTest() {
        OfferEdit offerEdit = mock(OfferEdit.class);
        unit.setOfferEdit(offerEdit);
        assertThat(unit.getOfferEdit()).isEqualTo(offerEdit);
    }

    @Test
    @DisplayName("Set and get errors")
    void errorsTest() {
        Errors errors = mock(Errors.class);
        unit.setErrors(errors);
        assertThat(unit.getErrors()).isEqualTo(errors);
    }

}
