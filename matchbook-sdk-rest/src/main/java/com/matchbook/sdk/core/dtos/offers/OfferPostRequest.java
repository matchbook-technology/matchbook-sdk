package com.matchbook.sdk.core.dtos.offers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.core.dtos.RestRequest;
import com.matchbook.sdk.core.dtos.prices.Side;

public class OfferPostRequest implements RestRequest {

    private final Long runnerId;
    private final Side side;
    private final BigDecimal odds;
    private final BigDecimal stake;
    private final boolean keepInPlay;

    private OfferPostRequest(OfferPostRequest.Builder builder) {
        this.runnerId = builder.runnerId;
        this.side = builder.side;
        this.odds = builder.odds;
        this.stake = builder.stake;
        this.keepInPlay = builder.keepInPlay;
    }

    public Long getRunnerId() {
        return runnerId;
    }

    public Side getSide() {
        return side;
    }

    public BigDecimal getOdds() {
        return odds;
    }

    public BigDecimal getStake() {
        return stake;
    }

    public boolean isKeepInPlay() {
        return keepInPlay;
    }

    @Override
    public String resourcePath() {
        return "";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return OfferPostRequest.class.getSimpleName() + " {" +
                "runnerId=" + runnerId +
                ", side=" + side +
                ", odds=" + odds +
                ", stake=" + stake +
                ", keepInPlay=" + keepInPlay +
                "}";
    }

    public static class Builder {

        private final Long runnerId;
        private final Side side;
        private final BigDecimal odds;
        private final BigDecimal stake;

        private boolean keepInPlay;

        public Builder(Long runnerId, Side side, BigDecimal odds, BigDecimal stake) {
            this.runnerId = runnerId;
            this.side = side;
            this.odds = odds;
            this.stake = stake;
            keepInPlay = false;
        }

        public Builder keepInPlay(boolean keepInPlay) {
            this.keepInPlay = keepInPlay;
            return this;
        }

        public OfferPostRequest build() {
            return new OfferPostRequest(this);
        }

    }

}
