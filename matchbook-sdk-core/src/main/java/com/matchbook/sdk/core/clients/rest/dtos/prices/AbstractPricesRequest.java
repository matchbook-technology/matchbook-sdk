package com.matchbook.sdk.core.clients.rest.dtos.prices;

import java.math.BigDecimal;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookPageableRequest;

public abstract class AbstractPricesRequest extends MatchbookPageableRequest {

    private OddsType oddsType;
    private Side side;
    private Currency currency;
    private BigDecimal minimumLiquidity;
    private PriceMode priceMode;

    public OddsType getOddsType() {
        return oddsType;
    }

    public void setOddsType(OddsType oddsType) {
        this.oddsType = oddsType;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getMinimumLiquidity() {
        return minimumLiquidity;
    }

    public void setMinimumLiquidity(BigDecimal minimumLiquidity) {
        this.minimumLiquidity = minimumLiquidity;
    }

    public PriceMode getPriceMode() {
        return priceMode;
    }

    public void setPriceMode(PriceMode priceMode) {
        this.priceMode = priceMode;
    }

}
