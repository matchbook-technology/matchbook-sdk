package com.matchbook.sdk.core.clients.rest.dtos.prices;

import com.matchbook.sdk.core.clients.rest.dtos.PageableRequest;

import java.math.BigDecimal;

public abstract class PageablePricesRequest extends PageableRequest {

    protected final OddsType oddsType;
    protected final Side side;
    protected final Currency currency;
    protected final BigDecimal minimumLiquidity;
    protected final PriceMode priceMode;

    protected <B extends PageablePricesRequestBuilder> PageablePricesRequest(B builder) {
        super(builder);

        this.oddsType = builder.oddsType;
        this.side = builder.side;
        this.currency = builder.currency;
        this.minimumLiquidity = builder.minimumLiquidity;
        this.priceMode = builder.priceMode;
    }

    public OddsType getOddsType() {
        return oddsType;
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
