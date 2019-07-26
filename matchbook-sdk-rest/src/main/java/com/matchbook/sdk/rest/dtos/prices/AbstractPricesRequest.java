package com.matchbook.sdk.rest.dtos.prices;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.matchbook.sdk.rest.dtos.RestRequest;

public abstract class AbstractPricesRequest implements RestRequest {

    protected final OddsType oddsType;
    protected final ExchangeType exchangeType;
    protected final Side side;
    protected final Currency currency;
    protected final BigDecimal minimumLiquidity;
    protected final PriceMode priceMode;

    protected <B extends AbstractPricesRequestBuilder> AbstractPricesRequest(B builder) {
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

    protected Map<String, String> pricesParameters() {
        Map<String, String> parameters = new HashMap<>();
        if (Objects.nonNull(currency)) {
            parameters.put("currency", currency.name());
        }
        if (Objects.nonNull(exchangeType)) {
            parameters.put("exchange-type", exchangeType.name());
        }
        if (Objects.nonNull(oddsType)) {
            parameters.put("odds-type", oddsType.name());
        }
        if (Objects.nonNull(side)) {
            parameters.put("side", side.name());
        }
        if (Objects.nonNull(minimumLiquidity)) {
            parameters.put("minimum-liquidity", minimumLiquidity.toPlainString());
        }
        if (Objects.nonNull(priceMode)) {
            parameters.put("price-mode", priceMode.name());
        }
        return parameters;
    }

}
