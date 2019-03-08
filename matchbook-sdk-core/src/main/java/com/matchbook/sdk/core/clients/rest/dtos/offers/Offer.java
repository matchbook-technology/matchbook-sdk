package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.matchbook.sdk.core.clients.rest.dtos.prices.Currency;
import com.matchbook.sdk.core.clients.rest.dtos.prices.OddsType;

public class Offer {

    private Long id;
    private Long eventId;
    private Long marketId;
    private Long runnerId;
    private String tempId;
    private String exchangeType;
    private BigDecimal odds;
    private OddsType oddsType;
    private BigDecimal decimalOdds;
    private BigDecimal stake;
    private BigDecimal remaining;
    private BigDecimal potentialProfit;
    private BigDecimal remainingPotentialProfit;
    private BigDecimal potentialLiability;
    private BigDecimal remainingPotentialLiability;
    private Currency currency;
    private String side;
    private Date createdTime;
    private String status;
    private List<MatchedBet> matchedBets;
    private List<Error> errors;

    public Offer() {
        matchedBets = new ArrayList<>();
        errors = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public BigDecimal getOdds() {
        return odds;
    }

    public void setOdds(BigDecimal odds) {
        this.odds = odds;
    }

    public OddsType getOddsType() {
        return oddsType;
    }

    public void setOddsType(OddsType oddsType) {
        this.oddsType = oddsType;
    }

    public BigDecimal getDecimalOdds() {
        return decimalOdds;
    }

    public void setDecimalOdds(BigDecimal decimalOdds) {
        this.decimalOdds = decimalOdds;
    }

    public BigDecimal getStake() {
        return stake;
    }

    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public BigDecimal getPotentialProfit() {
        return potentialProfit;
    }

    public void setPotentialProfit(BigDecimal potentialProfit) {
        this.potentialProfit = potentialProfit;
    }

    public BigDecimal getRemainingPotentialProfit() {
        return remainingPotentialProfit;
    }

    public void setRemainingPotentialProfit(BigDecimal remainingPotentialProfit) {
        this.remainingPotentialProfit = remainingPotentialProfit;
    }

    public BigDecimal getPotentialLiability() {
        return potentialLiability;
    }

    public void setPotentialLiability(BigDecimal potentialLiability) {
        this.potentialLiability = potentialLiability;
    }

    public BigDecimal getRemainingPotentialLiability() {
        return remainingPotentialLiability;
    }

    public void setRemainingPotentialLiability(BigDecimal remainingPotentialLiability) {
        this.remainingPotentialLiability = remainingPotentialLiability;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MatchedBet> getMatchedBets() {
        return matchedBets;
    }

    public void setMatchedBets(List<MatchedBet> matchedBets) {
        this.matchedBets = matchedBets;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
