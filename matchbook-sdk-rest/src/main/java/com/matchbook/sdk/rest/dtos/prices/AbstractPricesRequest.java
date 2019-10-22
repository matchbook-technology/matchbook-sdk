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

    protected AbstractPricesRequest(Init<?> init) {
        this.oddsType = init.oddsType;
        this.exchangeType = init.exchangeType;
        this.side = init.side;
        this.currency = init.currency;
        this.minimumLiquidity = init.minimumLiquidity;
        this.priceMode = init.priceMode;
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

    protected static abstract class Init<T extends Init<T>> {

        private OddsType oddsType;
        private ExchangeType exchangeType;
        private Side side;
        private Currency currency;
        private BigDecimal minimumLiquidity;
        private PriceMode priceMode;

        protected abstract T self();

        public T oddsType(OddsType oddsType) {
            this.oddsType = oddsType;
            return self();
        }

        public T exchangeType(ExchangeType exchangeType) {
            this.exchangeType = exchangeType;
            return self();
        }

        public T side(Side side) {
            this.side = side;
            return self();
        }

        public T currency(Currency currency) {
            this.currency = currency;
            return self();
        }

        public T minimumLiquidity(BigDecimal minimumLiquidity) {
            this.minimumLiquidity = minimumLiquidity;
            return self();
        }

        public T priceMode(PriceMode priceMode) {
            this.priceMode = priceMode;
            return self();
        }

    }

    public static class Builder extends Init<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
    }

}
