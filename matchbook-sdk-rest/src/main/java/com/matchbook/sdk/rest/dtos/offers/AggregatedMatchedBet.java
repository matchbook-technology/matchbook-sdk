package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.RestResponse;
import com.matchbook.sdk.rest.dtos.prices.Side;

public class AggregatedMatchedBet extends AbstractMatchedBet implements RestResponse {

    private Long eventId;
    private Long marketId;
    private Long runnerId;
    private Side side;
    private String eventName;
    private String marketName;
    private String runnerName;

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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    @Override
    public String toString() {
        return AggregatedMatchedBet.class.getSimpleName() + " {" +
                "eventId=" + eventId +
                ", eventName=" + eventName +
                ", marketId=" + marketId +
                ", marketName=" + marketName +
                ", runnerId=" + runnerId +
                ", runnerName=" + runnerName +
                ", status=" + status +
                ", oddsType=" + oddsType +
                ", currency=" + currency +
                ", odds=" + odds +
                ", decimalOdds=" + decimalOdds +
                ", stake=" + stake +
                ", potentialProfit=" + potentialProfit +
                ", potentialLiability=" + potentialLiability +
                ", side=" + side +
                "}";
    }

}
