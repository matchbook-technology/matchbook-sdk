package com.matchbook.sdk.rest.readers.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditStatus;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;
import com.matchbook.sdk.rest.readers.errors.ErrorReader;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class OfferEditReaderTest extends ResponseReaderTest<OfferEditReader> {

    @Mock
    private ErrorReader errorReader;

    @Override
    protected OfferEditReader newReader() {
        return new OfferEditReader(errorReader);
    }

    @Test
    @DisplayName("Read next item with no errors")
    void readNextItemNoErrorsTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false,
                false, false, false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("id", "offer-id", "runner-id", "odds-type",
                "odds-before", "odds-after", "stake-before", "stake-after", "status", "delay", "edit-time", "errors");
        when(parser.getLong()).thenReturn(425098661640113L, 425096769380013L, 395750978870010L);
        when(parser.getEnum(OddsType.class)).thenReturn(OddsType.DECIMAL);
        when(parser.getEnum(OfferEditStatus.class)).thenReturn(OfferEditStatus.DELAYED);
        when(parser.getDouble()).thenReturn(1.5d, 1.65d, 8d);
        when(parser.getDecimal()).thenReturn(new BigDecimal("130"), new BigDecimal("140.50"));
        Instant editTime = Instant.now();
        when(parser.getInstant()).thenReturn(editTime);

        when(parser.isEndOfArray()).thenReturn(true);

        OfferEdit offerEdit = unit.readNextItem();

        assertThat(offerEdit).isNotNull();
        assertThat(offerEdit.getId()).isEqualTo(425098661640113L);
        assertThat(offerEdit.getOfferId()).isEqualTo(425096769380013L);
        assertThat(offerEdit.getRunnerId()).isEqualTo(395750978870010L);
        assertThat(offerEdit.getOddsType()).isEqualTo(OddsType.DECIMAL);
        assertThat(offerEdit.getOddsBefore()).isEqualTo(1.5d);
        assertThat(offerEdit.getOddsAfter()).isEqualTo(1.65d);
        assertThat(offerEdit.getStakeBefore()).isEqualByComparingTo(new BigDecimal("130"));
        assertThat(offerEdit.getStakeAfter()).isEqualByComparingTo(new BigDecimal("140.5"));
        assertThat(offerEdit.getStatus()).isEqualTo(OfferEditStatus.DELAYED);
        assertThat(offerEdit.getDelay()).isEqualTo(8d);
        assertThat(offerEdit.getEditTime()).isCloseTo(editTime, within(1L, ChronoUnit.SECONDS));
        assertThat(offerEdit.getErrors()).isNull();
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

        OfferEdit offerEdit = unit.readNextItem();

        assertThat(offerEdit).isNotNull();
        assertThat(offerEdit.getErrors()).isNotNull();
        assertThat(offerEdit.getErrors().getErrors()).containsOnly(error);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
