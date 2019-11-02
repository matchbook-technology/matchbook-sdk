package com.matchbook.sdk.rest.readers.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.dtos.events.MarketType;
import com.matchbook.sdk.rest.dtos.offers.CommissionType;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;
import com.matchbook.sdk.rest.dtos.offers.Offer;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferStatus;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.Side;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;
import com.matchbook.sdk.rest.readers.errors.ErrorReader;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class OfferReaderTest extends ResponseReaderTest<OfferReader> {

    @Mock
    private MatchedBetReader matchedBetReader;

    @Mock
    private OfferEditReader offerEditReader;

    @Mock
    private ErrorReader errorReader;

    @Override
    protected OfferReader newReader() {
        return new OfferReader(matchedBetReader, offerEditReader, errorReader);
    }

    @Test
    @DisplayName("Read next item with no errors")
    void readNextItemNoErrorsTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("id", "event-id", "event-name", "market-id", "market-name",
                "market-type", "runner-id", "runner-name", "exchange-type", "side", "odds-type", "odds", "decimal-odds",
                "currency", "stake", "remaining", "potential-liability", "remaining-potential-liability",
                "commission-type", "originator-commission-rate", "acceptor-commission-rate", "commission-reserve",
                "status", "created-at", "in-play", "keep-in-play", "offer-edit", "matched-bets", "errors");
        when(parser.getLong()).thenReturn(425096769380013L, 395729780570010L, 395729860260010L, 395750978870010L);
        when(parser.getString()).thenReturn("CSKA Moscow vs Bayer 04 Leverkusen", "Over/Under 1.5", "OVER 1.5");
        when(parser.getEnum(MarketType.class)).thenReturn(MarketType.TOTAL);
        when(parser.getEnum(ExchangeType.class)).thenReturn(ExchangeType.BACK_LAY);
        when(parser.getEnum(Side.class)).thenReturn(Side.LAY);
        when(parser.getEnum(OddsType.class)).thenReturn(OddsType.DECIMAL);
        when(parser.getEnum(Currency.class)).thenReturn(Currency.EUR);
        when(parser.getEnum(CommissionType.class)).thenReturn(CommissionType.NET_WIN);
        when(parser.getEnum(OfferStatus.class)).thenReturn(OfferStatus.EDITED);
        when(parser.getDouble()).thenReturn(2.2d, 2.2d, 0.15d, 0.3d);
        when(parser.getDecimal()).thenReturn(new BigDecimal("50"), new BigDecimal("1786.35"), new BigDecimal("60"),
                new BigDecimal("2143.62"), new BigDecimal("1.80"));
        when(parser.getBoolean()).thenReturn(true, false);
        Instant createdAt = Instant.now();
        when(parser.getInstant()).thenReturn(createdAt);

        when(parser.isEndOfArray()).thenReturn(false, true);
        OfferEdit offerEdit = mock(OfferEdit.class);
        when(offerEditReader.readFullResponse()).thenReturn(offerEdit);
        MatchedBet matchedBet = mock(MatchedBet.class);
        when(matchedBetReader.readFullResponse()).thenReturn(matchedBet);

        Offer offer = unit.readNextItem();

        assertThat(offer).isNotNull();
        assertThat(offer.getId()).isEqualTo(425096769380013L);
        assertThat(offer.getEventId()).isEqualTo(395729780570010L);
        assertThat(offer.getMarketId()).isEqualTo(395729860260010L);
        assertThat(offer.getRunnerId()).isEqualTo(395750978870010L);
        assertThat(offer.getEventName()).isEqualTo("CSKA Moscow vs Bayer 04 Leverkusen");
        assertThat(offer.getMarketName()).isEqualTo("Over/Under 1.5");
        assertThat(offer.getRunnerName()).isEqualTo("OVER 1.5");
        assertThat(offer.getMarketType()).isEqualTo(MarketType.TOTAL);
        assertThat(offer.getExchangeType()).isEqualTo(ExchangeType.BACK_LAY);
        assertThat(offer.getSide()).isEqualTo(Side.LAY);
        assertThat(offer.getOddsType()).isEqualTo(OddsType.DECIMAL);
        assertThat(offer.getOdds()).isEqualTo(2.2d);
        assertThat(offer.getDecimalOdds()).isEqualTo(2.2d);
        assertThat(offer.getStake()).isEqualByComparingTo(new BigDecimal("50"));
        assertThat(offer.getRemaining()).isEqualByComparingTo(new BigDecimal("1786.35"));
        assertThat(offer.getPotentialLiability()).isEqualByComparingTo(new BigDecimal("60"));
        assertThat(offer.getRemainingPotentialLiability()).isEqualByComparingTo(new BigDecimal("2143.62"));
        assertThat(offer.getCommissionType()).isEqualTo(CommissionType.NET_WIN);
        assertThat(offer.getOriginatorCommissionRate()).isEqualTo(0.15d);
        assertThat(offer.getAcceptorCommissionRate()).isEqualTo(0.3d);
        assertThat(offer.getCommissionReserve()).isEqualByComparingTo(new BigDecimal("1.8"));
        assertThat(offer.getStatus()).isEqualTo(OfferStatus.EDITED);
        assertThat(offer.getCreatedAt()).isCloseTo(createdAt, within(1L, ChronoUnit.SECONDS));
        assertThat(offer.isInPlay()).isTrue();
        assertThat(offer.isKeepInPlay()).isFalse();
        assertThat(offer.getOfferEdit()).isEqualTo(offerEdit);
        assertThat(offer.getMatchedBets()).containsOnly(matchedBet);
        assertThat(offer.getErrors()).isNull();
        assertThat(unit.hasMoreItems()).isFalse();
    }

    @Test
    @DisplayName("Read next item with errors")
    void readNextItemWithErrorsTest() {
        when(parser.isEndOfObject()).thenReturn(false, true);
        when(parser.getFieldName()).thenReturn("errors");

        when(parser.isEndOfArray()).thenReturn(false, true);
        Error error = mock(Error.class);
        when(errorReader.readFullResponse()).thenReturn(error);

        Offer offer = unit.readNextItem();

        assertThat(offer).isNotNull();
        assertThat(offer.getErrors()).isNotNull();
        assertThat(offer.getErrors().getErrors()).containsOnly(error);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
