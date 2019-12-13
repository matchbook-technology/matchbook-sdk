package com.matchbook.sdk.rest.dtos.offers;

import java.math.BigDecimal;
import java.time.Instant;

import com.matchbook.sdk.rest.dtos.FailableRestResponse;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

public class OfferEdit implements FailableRestResponse {

    private Long id;
    private Long offerId;
    private Long runnerId;
    private OfferEditStatus status;
    private OddsType oddsType;
    private Double oddsBefore;
    private Double oddsAfter;
    private BigDecimal stakeBefore;
    private BigDecimal stakeAfter;
    private Double delay;
    private Instant editTime;
    private Errors errors;

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

    public Long getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(Long runnerId) {
        this.runnerId = runnerId;
    }

    public OfferEditStatus getStatus() {
        return status;
    }

    public void setStatus(OfferEditStatus status) {
        this.status = status;
    }

    public OddsType getOddsType() {
        return oddsType;
    }

    public void setOddsType(OddsType oddsType) {
        this.oddsType = oddsType;
    }

    public Double getOddsBefore() {
        return oddsBefore;
    }

    public void setOddsBefore(Double oddsBefore) {
        this.oddsBefore = oddsBefore;
    }

    public Double getOddsAfter() {
        return oddsAfter;
    }

    public void setOddsAfter(Double oddsAfter) {
        this.oddsAfter = oddsAfter;
    }

    public BigDecimal getStakeBefore() {
        return stakeBefore;
    }

    public void setStakeBefore(BigDecimal stakeBefore) {
        this.stakeBefore = stakeBefore;
    }

    public BigDecimal getStakeAfter() {
        return stakeAfter;
    }

    public void setStakeAfter(BigDecimal stakeAfter) {
        this.stakeAfter = stakeAfter;
    }

    public Double getDelay() {
        return delay;
    }

    public void setDelay(Double delay) {
        this.delay = delay;
    }

    public Instant getEditTime() {
        return editTime;
    }

    public void setEditTime(Instant editTime) {
        this.editTime = editTime;
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
        return OfferEdit.class.getSimpleName() + " {" +
                "id=" + id +
                ", offerId=" + offerId +
                ", runnerId=" + runnerId +
                ", status=" + status +
                ", oddsType=" + oddsType +
                ", oddsBefore=" + oddsBefore +
                ", oddsAfter=" + oddsAfter +
                ", stakeBefore=" + stakeBefore +
                ", stakeAfter=" + stakeAfter +
                ", delay=" + delay +
                ", editTime=" + editTime +
                ", errors=" + errors +
                "}";
    }

}
