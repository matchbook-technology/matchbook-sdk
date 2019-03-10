package com.matchbook.sdk.core.clients.rest.dtos.prices;

import java.math.BigDecimal;

public abstract class BasePricesRequestBuilder {

    OddsType oddsType;
    Side side;
    Currency currency;
    BigDecimal minimumLiquidity;
    PriceMode priceMode;

    public BasePricesRequestBuilder oddsType(OddsType oddsType) {
        this.oddsType = oddsType;
        return this;
    }

    public BasePricesRequestBuilder side(Side side) {
        this.side = side;
        return this;
    }

    public BasePricesRequestBuilder currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public BasePricesRequestBuilder minimumLiquidity(BigDecimal minimumLiquidity) {
        this.minimumLiquidity = minimumLiquidity;
        return this;
    }

    public BasePricesRequestBuilder priceMode(PriceMode priceMode) {
        this.priceMode = priceMode;
        return this;
    }

}
