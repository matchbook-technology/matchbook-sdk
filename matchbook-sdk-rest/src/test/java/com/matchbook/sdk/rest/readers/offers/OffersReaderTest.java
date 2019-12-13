package com.matchbook.sdk.rest.readers.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBet;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBetsResponse;
import com.matchbook.sdk.rest.dtos.offers.Offer;
import com.matchbook.sdk.rest.dtos.offers.OffersResponse;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.readers.PageableResponseReaderTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class OffersReaderTest extends PageableResponseReaderTest<OffersReader, Offer, OffersResponse> {

    @Mock
    private OfferReader offerReader;

    @Mock
    private Offer offer;

    @Override
    protected OffersReader newReader() {
        return new OffersReader(offerReader);
    }

    @Override
    protected OfferReader getItemsReader() {
        return offerReader;
    }

    @Override
    protected Offer getItem() {
        return offer;
    }

    @Override
    protected String getItemsFieldName() {
        return "offers";
    }

    @Test
    @Override
    @DisplayName("Read full response")
    protected void readFullResponseTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("total", "offset", "per-page",
                "currency", "exchange-type", "odds-type", "offers");
        when(parser.getInteger()).thenReturn(42, 0, 20);
        when(parser.getEnum(Currency.class)).thenReturn(Currency.CAD);
        when(parser.getEnum(ExchangeType.class)).thenReturn(ExchangeType.BINARY);
        when(parser.getEnum(OddsType.class)).thenReturn(OddsType.INDO);

        when(parser.isEndOfArray()).thenReturn(false, true);
        when(offerReader.readFullResponse()).thenReturn(offer);

        OffersResponse offers = unit.readFullResponse();

        assertThat(offers).isNotNull();
        assertThat(offers.getTotal()).isEqualTo(42);
        assertThat(offers.getOffset()).isEqualTo(0);
        assertThat(offers.getPerPage()).isEqualTo(20);
        assertThat(offers.getCurrency()).isEqualTo(Currency.CAD);
        assertThat(offers.getExchangeType()).isEqualTo(ExchangeType.BINARY);
        assertThat(offers.getOddsType()).isEqualTo(OddsType.INDO);
        assertThat(offers.getItems()).containsOnly(offer);
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
