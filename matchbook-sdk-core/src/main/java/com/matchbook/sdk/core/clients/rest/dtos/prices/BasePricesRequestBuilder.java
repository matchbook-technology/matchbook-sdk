package com.matchbook.sdk.core.clients.rest.dtos.prices;

import java.math.BigDecimal;

public abstract class BasePricesRequestBuilder {

    protected OddsType oddsType;
    protected ExchangeType exchangeType;
    protected Side side;
    protected Currency currency;
    protected BigDecimal minimumLiquidity;
    protected PriceMode priceMode;

    public BasePricesRequestBuilder oddsType(OddsType oddsType) {
        this.oddsType = oddsType;
        return this;
    }

    public BasePricesRequestBuilder exchangeType(ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
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
