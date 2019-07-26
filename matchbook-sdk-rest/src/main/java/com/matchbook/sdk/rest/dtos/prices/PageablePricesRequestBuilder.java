package com.matchbook.sdk.rest.dtos.prices;

import com.matchbook.sdk.rest.dtos.PageableRequestBuilder;

import java.math.BigDecimal;

public abstract class PageablePricesRequestBuilder extends PageableRequestBuilder {

    protected OddsType oddsType;
    protected ExchangeType exchangeType;
    protected Side side;
    protected Currency currency;
    protected BigDecimal minimumLiquidity;
    protected PriceMode priceMode;

    public PageablePricesRequestBuilder oddsType(OddsType oddsType) {
        this.oddsType = oddsType;
        return this;
    }

    public PageablePricesRequestBuilder exchangeType(ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
        return this;
    }

    public PageablePricesRequestBuilder side(Side side) {
        this.side = side;
        return this;
    }

    public PageablePricesRequestBuilder currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public PageablePricesRequestBuilder minimumLiquidity(BigDecimal minimumLiquidity) {
        this.minimumLiquidity = minimumLiquidity;
        return this;
    }

    public PageablePricesRequestBuilder priceMode(PriceMode priceMode) {
        this.priceMode = priceMode;
        return this;
    }

}
