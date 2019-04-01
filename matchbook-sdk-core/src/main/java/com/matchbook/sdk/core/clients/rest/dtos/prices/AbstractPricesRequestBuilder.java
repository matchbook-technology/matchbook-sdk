package com.matchbook.sdk.core.clients.rest.dtos.prices;

import java.math.BigDecimal;

public abstract class AbstractPricesRequestBuilder {

    protected OddsType oddsType;
    protected ExchangeType exchangeType;
    protected Side side;
    protected Currency currency;
    protected BigDecimal minimumLiquidity;
    protected PriceMode priceMode;

    public AbstractPricesRequestBuilder oddsType(OddsType oddsType) {
        this.oddsType = oddsType;
        return this;
    }

    public AbstractPricesRequestBuilder exchangeType(ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
        return this;
    }

    public AbstractPricesRequestBuilder side(Side side) {
        this.side = side;
        return this;
    }

    public AbstractPricesRequestBuilder currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public AbstractPricesRequestBuilder minimumLiquidity(BigDecimal minimumLiquidity) {
        this.minimumLiquidity = minimumLiquidity;
        return this;
    }

    public AbstractPricesRequestBuilder priceMode(PriceMode priceMode) {
        this.priceMode = priceMode;
        return this;
    }

}
