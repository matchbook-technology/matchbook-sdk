package com.matchbook.sdk.rest.readers.prices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.Price;
import com.matchbook.sdk.rest.dtos.prices.Side;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PriceReaderTest extends ResponseReaderTest<PriceReader> {

    @Override
    protected PriceReader newReader() {
        return new PriceReader();
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false, false, true);
        when(parser.getFieldName()).thenReturn("exchange-type", "side", "odds-type", "currency", "odds", "available-amount");
        when(parser.getEnum(ExchangeType.class)).thenReturn(ExchangeType.BACK_LAY);
        when(parser.getEnum(Side.class)).thenReturn(Side.BACK);
        when(parser.getEnum(OddsType.class)).thenReturn(OddsType.DECIMAL);
        when(parser.getEnum(Currency.class)).thenReturn(Currency.GBP);
        when(parser.getDouble()).thenReturn(5.2d);
        when(parser.getDecimal()).thenReturn(new BigDecimal("250"));

        Price price = unit.readNextItem();

        assertThat(price).isNotNull();
        assertThat(price.getExchangeType()).isEqualTo(ExchangeType.BACK_LAY);
        assertThat(price.getSide()).isEqualTo(Side.BACK);
        assertThat(price.getOddsType()).isEqualTo(OddsType.DECIMAL);
        assertThat(price.getCurrency()).isEqualTo(Currency.GBP);
        assertThat(price.getOdds()).isEqualTo(5.2d);
        assertThat(price.getAvailableAmount()).isEqualByComparingTo(new BigDecimal("250"));
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
