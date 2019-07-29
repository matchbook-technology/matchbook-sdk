package com.matchbook.sdk.rest.dtos.offers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import com.matchbook.sdk.rest.dtos.RestResponse;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

public class OfferEdit implements RestResponse<OfferEdit> {

    private Long id;
    private Long offerId;
    private Long runnerId;
    private OfferEditStatus status;
    private OddsType oddsType;
    private BigDecimal oddsBefore;
    private BigDecimal oddsAfter;
    private BigDecimal stakeBefore;
    private BigDecimal stakeAfter;
    private BigDecimal delay;
    private Date editTime;

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

    public BigDecimal getOddsBefore() {
        return oddsBefore;
    }

    public void setOddsBefore(BigDecimal oddsBefore) {
        this.oddsBefore = oddsBefore;
    }

    public BigDecimal getOddsAfter() {
        return oddsAfter;
    }

    public void setOddsAfter(BigDecimal oddsAfter) {
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

    public BigDecimal getDelay() {
        return delay;
    }

    public void setDelay(BigDecimal delay) {
        this.delay = delay;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    @Override
    public Set<OfferEdit> getContent() {
        return Collections.singleton(this);
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
                "}";
    }

}
