package com.matchbook.sdk.rest.dtos.prices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.matchbook.sdk.rest.dtos.PageableRequestTest;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class PageablePricesRequestTest<T extends PageablePricesRequest> extends PageableRequestTest<T> {

    private OddsType oddsType;
    private ExchangeType exchangeType;
    private Side side;
    private Currency currency;
    private BigDecimal minimumLiquidity;
    private PriceMode priceMode;

    protected abstract T newPageablePricesRequest(OddsType oddsType, ExchangeType exchangeType, Side side,
            Currency currency, BigDecimal minimumLiquidity, PriceMode priceMode, int offset, int perPage);

    @Override
    @BeforeEach
    protected void setUp() {
        oddsType = OddsType.DECIMAL;
        exchangeType = ExchangeType.BACK_LAY;
        side = Side.LAY;
        currency = Currency.EUR;
        minimumLiquidity = BigDecimal.TEN;
        priceMode = PriceMode.EXPANDED;

        super.setUp();
    }

    @Override
    protected T newPageableRequest(int offset, int perPage) {
        return newPageablePricesRequest(oddsType, exchangeType, side, currency, minimumLiquidity, priceMode, offset, perPage);
    }

    @Test
    @DisplayName("Check odds type")
    void oddsTypeTest() {
        OddsType actualOddsType = unit.getOddsType();
        assertThat(actualOddsType).isEqualTo(oddsType);

        assertThat(emptyUnit.getOddsType()).isNull();
    }

    @Test
    @DisplayName("Check exchange type")
    void exchangeTypeTest() {
        ExchangeType actualExchangeType = unit.getExchangeType();
        assertThat(actualExchangeType).isEqualTo(exchangeType);

        assertThat(emptyUnit.getExchangeType()).isNull();
    }

    @Test
    @DisplayName("Check side")
    void sideTest() {
        Side actualSide = unit.getSide();
        assertThat(actualSide).isEqualTo(side);

        assertThat(emptyUnit.getSide()).isNull();
    }

    @Test
    @DisplayName("Check currency")
    void currencyTest() {
        Currency actualCurrency = unit.getCurrency();
        assertThat(actualCurrency).isEqualTo(currency);

        assertThat(emptyUnit.getCurrency()).isNull();
    }

    @Test
    @DisplayName("Check minimum liquidity")
    void minimumLiquidityTest() {
        BigDecimal actualMinimumLiquidity = unit.getMinimumLiquidity();
        assertThat(actualMinimumLiquidity).isEqualByComparingTo(minimumLiquidity);

        assertThat(emptyUnit.getMinimumLiquidity()).isNull();
    }

    @Test
    @DisplayName("Check price mode")
    void priceModeTest() {
        PriceMode actualPriceMode = unit.getPriceMode();
        assertThat(actualPriceMode).isEqualTo(priceMode);

        assertThat(emptyUnit.getPriceMode()).isNull();
    }

    @Test
    @DisplayName("Check prices query parameters")
    void pricesParametersTest() {
        assertThat(unit.parameters())
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(
                        tuple("odds-type", oddsType.name()),
                        tuple("exchange-type", exchangeType.name()),
                        tuple("side", side.name()),
                        tuple("currency", currency.name()),
                        tuple("minimum-liquidity", minimumLiquidity.toPlainString()),
                        tuple("price-mode", priceMode.name())
                );

        assertThat(emptyUnit.parameters())
                .doesNotContainKeys("odds-type", "exchange-type", "side", "currency", "minimum-liquidity", "price-mode");
    }

}
