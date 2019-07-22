package com.matchbook.sdk.core.dtos.offers;

import java.util.Collections;
import java.util.Set;

import com.matchbook.sdk.core.dtos.RestResponse;
import com.matchbook.sdk.core.dtos.prices.Side;

public class AggregatedMatchedBet extends AbstractMatchedBet implements RestResponse<AggregatedMatchedBet> {

    private Long eventId;
    private Long marketId;
    private Long runnerId;
    private Side side;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(Long runnerId) {
        this.runnerId = runnerId;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public Set<AggregatedMatchedBet> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return AggregatedMatchedBet.class.getSimpleName() + " {" +
                "eventId=" + eventId +
                ", marketId=" + marketId +
                ", runnerId=" + runnerId +
                ", status=" + status +
                ", oddsType=" + oddsType +
                ", currency=" + currency +
                ", odds=" + odds +
                ", stake=" + stake +
                ", potentialProfit=" + potentialProfit +
                ", potentialLiability=" + potentialLiability +
                ", side=" + side +
                "}";
    }

}
