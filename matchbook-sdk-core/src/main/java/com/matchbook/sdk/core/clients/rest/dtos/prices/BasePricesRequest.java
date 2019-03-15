package com.matchbook.sdk.core.clients.rest.dtos.prices;

import java.math.BigDecimal;

import com.matchbook.sdk.core.clients.rest.dtos.RestRequest;

public abstract class BasePricesRequest implements RestRequest {

    protected final OddsType oddsType;
    protected final ExchangeType exchangeType;
    protected final Side side;
    protected final Currency currency;
    protected final BigDecimal minimumLiquidity;
    protected final PriceMode priceMode;

    protected <B extends BasePricesRequestBuilder> BasePricesRequest(B builder) {
        this.oddsType = builder.oddsType;
        this.exchangeType = builder.exchangeType;
        this.side = builder.side;
        this.currency = builder.currency;
        this.minimumLiquidity = builder.minimumLiquidity;
        this.priceMode = builder.priceMode;
    }

    public OddsType getOddsType() {
        return oddsType;
    }

    public ExchangeType getExchangeType() {
        return exchangeType;
    }

    public Side getSide() {
        return side;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getMinimumLiquidity() {
        return minimumLiquidity;
    }

    public PriceMode getPriceMode() {
        return priceMode;
    }

}
