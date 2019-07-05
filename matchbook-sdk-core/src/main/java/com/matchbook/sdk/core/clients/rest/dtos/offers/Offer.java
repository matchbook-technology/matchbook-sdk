package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;
import com.matchbook.sdk.core.clients.rest.dtos.prices.Currency;
import com.matchbook.sdk.core.clients.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.core.clients.rest.dtos.prices.OddsType;
import com.matchbook.sdk.core.clients.rest.dtos.prices.Side;

public class Offer implements RestResponse<Offer> {

    private Long id;
    private Long eventId;
    private Long marketId;
    private Long runnerId;
    private OfferStatus status;
    private ExchangeType exchangeType;
    private Side side;
    private OddsType oddsType;
    private Currency currency;
    private BigDecimal odds;
    private BigDecimal stake;
    private BigDecimal remaining;
    private BigDecimal potentialLiability;
    private BigDecimal remainingPotentialLiability;
    private CommissionType commissionType;
    private BigDecimal originatorCommissionRate;
    private BigDecimal acceptorCommissionRate;
    private BigDecimal commissionReserve;
    private boolean inPlay;
    private boolean keepInPlay;
    private Instant createdAt;
    private List<MatchedBet> matchedBets;

    public Offer() {
        matchedBets = new ArrayList<>();
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

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public ExchangeType getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
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

    public BigDecimal getOdds() {
        return odds;
    }

    public void setOdds(BigDecimal odds) {
        this.odds = odds;
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

    public CommissionType getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(CommissionType commissionType) {
        this.commissionType = commissionType;
    }

    public BigDecimal getOriginatorCommissionRate() {
        return originatorCommissionRate;
    }

    public void setOriginatorCommissionRate(BigDecimal originatorCommissionRate) {
        this.originatorCommissionRate = originatorCommissionRate;
    }

    public BigDecimal getAcceptorCommissionRate() {
        return acceptorCommissionRate;
    }

    public void setAcceptorCommissionRate(BigDecimal acceptorCommissionRate) {
        this.acceptorCommissionRate = acceptorCommissionRate;
    }

    public BigDecimal getCommissionReserve() {
        return commissionReserve;
    }

    public void setCommissionReserve(BigDecimal commissionReserve) {
        this.commissionReserve = commissionReserve;
    }

    public boolean isInPlay() {
        return inPlay;
    }

    public void setInPlay(boolean inPlay) {
        this.inPlay = inPlay;
    }

    public boolean isKeepInPlay() {
        return keepInPlay;
    }

    public void setKeepInPlay(boolean keepInPlay) {
        this.keepInPlay = keepInPlay;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<MatchedBet> getMatchedBets() {
        return matchedBets;
    }

    public void setMatchedBets(List<MatchedBet> matchedBets) {
        this.matchedBets = matchedBets;
    }

    @Override
    public Set<Offer> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return Offer.class.getSimpleName() + " {" +
                "id=" + id +
                ", eventId=" + eventId +
                ", marketId=" + marketId +
                ", runnerId=" + runnerId +
                ", status=" + status +
                ", exchangeType=" + exchangeType +
                ", side=" + side +
                ", oddsType=" + oddsType +
                ", currency=" + currency +
                ", odds=" + odds +
                ", stake=" + stake +
                ", remaining=" + remaining +
                ", potentialLiability=" + potentialLiability +
                ", remainingPotentialLiability=" + remainingPotentialLiability +
                ", commissionType=" + commissionType +
                ", originatorCommissionRate=" + originatorCommissionRate +
                ", acceptorCommissionRate=" + acceptorCommissionRate +
                ", commissionReserve=" + commissionReserve +
                ", inPlay=" + inPlay +
                ", keepInPlay=" + keepInPlay +
                ", createdAt=" + createdAt +
                ", matchedBets=" + matchedBets +
                "}";
    }

}
