package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.RestResponse;

import java.math.BigDecimal;

public class Position implements RestResponse {

    private Long eventId;
    private Long marketId;
    private Long runnerId;
    private BigDecimal potentialProfit;
    private BigDecimal potentialLoss;

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

    public BigDecimal getPotentialProfit() {
        return potentialProfit;
    }

    public void setPotentialProfit(BigDecimal potentialProfit) {
        this.potentialProfit = potentialProfit;
    }

    public BigDecimal getPotentialLoss() {
        return potentialLoss;
    }

    public void setPotentialLoss(BigDecimal potentialLoss) {
        this.potentialLoss = potentialLoss;
    }

    @Override
    public String toString() {
        return Position.class.getSimpleName() + " {" +
                "eventId=" + eventId +
                ", marketId=" + marketId +
                ", runnerId=" + runnerId +
                ", potentialProfit=" + potentialProfit +
                ", potentialLoss=" + potentialLoss +
                "}";
    }

}
