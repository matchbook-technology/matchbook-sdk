package com.matchbook.sdk.rest.dtos.offers;

import com.matchbook.sdk.rest.dtos.FailableRestResponse;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.dtos.events.MarketType;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.Side;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Offer implements FailableRestResponse {

    private Long id;
    private Long eventId;
    private Long marketId;
    private Long runnerId;
    private String eventName;
    private String marketName;
    private String runnerName;
    private MarketType marketType;
    private OfferStatus status;
    private ExchangeType exchangeType;
    private Side side;
    private OddsType oddsType;
    private Currency currency;
    private Double odds;
    private Double decimalOdds;
    private BigDecimal stake;
    private BigDecimal remaining;
    private BigDecimal potentialLiability;
    private BigDecimal remainingPotentialLiability;
    private CommissionType commissionType;
    private Double originatorCommissionRate;
    private Double acceptorCommissionRate;
    private BigDecimal commissionReserve;
    private boolean inPlay;
    private boolean keepInPlay;
    private Instant createdAt;
    private List<MatchedBet> matchedBets;
    private OfferEdit offerEdit;
    private Errors errors;

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

    public MarketType getMarketType() {
        return marketType;
    }

    public void setMarketType(MarketType marketType) {
        this.marketType = marketType;
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

    public Double getOriginatorCommissionRate() {
        return originatorCommissionRate;
    }

    public void setOriginatorCommissionRate(Double originatorCommissionRate) {
        this.originatorCommissionRate = originatorCommissionRate;
    }

    public Double getAcceptorCommissionRate() {
        return acceptorCommissionRate;
    }

    public void setAcceptorCommissionRate(Double acceptorCommissionRate) {
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

    public OfferEdit getOfferEdit() {
        return offerEdit;
    }

    public void setOfferEdit(OfferEdit offerEdit) {
        this.offerEdit = offerEdit;
    }

    @Override
    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return Offer.class.getSimpleName() + " {" +
                "id=" + id +
                ", eventId=" + eventId +
                ", marketId=" + marketId +
                ", runnerId=" + runnerId +
                ", eventName=" + eventName +
                ", marketName=" + marketName +
                ", runnerName=" + runnerName +
                ", marketType=" + marketType +
                ", status=" + status +
                ", exchangeType=" + exchangeType +
                ", side=" + side +
                ", oddsType=" + oddsType +
                ", currency=" + currency +
                ", odds=" + odds +
                ", decimalOdds=" + decimalOdds +
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
                ", offerEdit=" + offerEdit +
                ", errors=" + errors +
                "}";
    }

}
