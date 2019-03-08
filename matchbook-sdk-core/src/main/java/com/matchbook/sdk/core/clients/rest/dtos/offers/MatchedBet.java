package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.math.BigDecimal;
import java.util.Date;

import com.matchbook.sdk.core.clients.rest.dtos.prices.Currency;
import com.matchbook.sdk.core.clients.rest.dtos.prices.OddsType;

public class MatchedBet {

    private Long id;
    private Long offerId;
    private BigDecimal odds;
    private OddsType oddsType;
    private BigDecimal decimalOdds;
    private BigDecimal stake;
    private BigDecimal commission;
    private Currency currency;
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
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

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
