package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.math.BigDecimal;

public class Position {


    private Long eventId;
    private Long marketId;
    private Long runnerId;
    private BigDecimal potentialProfit;
    private BigDecimal potentialLoss;
    private BigDecimal pnlOnWin;
    private BigDecimal pnlOnLose;

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

    public BigDecimal getPnlOnWin() {
        return pnlOnWin;
    }

    public void setPnlOnWin(BigDecimal pnlOnWin) {
        this.pnlOnWin = pnlOnWin;
    }

    public BigDecimal getPnlOnLose() {
        return pnlOnLose;
    }

    public void setPnlOnLose(BigDecimal pnlOnLose) {
        this.pnlOnLose = pnlOnLose;
    }
}
