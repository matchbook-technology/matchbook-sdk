package com.matchbook.sdk.rest.dtos.offers;

import java.math.BigDecimal;

import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

public abstract class AbstractMatchedBet {

    protected MatchedBetStatus status;
    protected OddsType oddsType;
    protected Currency currency;
    protected Double odds;
    protected Double decimalOdds;
    protected BigDecimal stake;
    protected BigDecimal potentialProfit;
    protected BigDecimal potentialLiability;

    public MatchedBetStatus getStatus() {
        return status;
    }

    public void setStatus(MatchedBetStatus status) {
        this.status = status;
    }

    public OddsType getOddsType() {
        return oddsType;
    }

    public void setOddsType(OddsType oddsType) {
        this.oddsType = oddsType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getOdds() {
        return odds;
    }

    public void setOdds(Double odds) {
        this.odds = odds;
    }

    public Double getDecimalOdds() {
        return decimalOdds;
    }

    public void setDecimalOdds(Double decimalOdds) {
        this.decimalOdds = decimalOdds;
    }

    public BigDecimal getStake() {
        return stake;
    }

    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    public BigDecimal getPotentialProfit() {
        return potentialProfit;
    }

    public void setPotentialProfit(BigDecimal potentialProfit) {
        this.potentialProfit = potentialProfit;
    }

    public BigDecimal getPotentialLiability() {
        return potentialLiability;
    }

    public void setPotentialLiability(BigDecimal potentialLiability) {
        this.potentialLiability = potentialLiability;
    }

}
