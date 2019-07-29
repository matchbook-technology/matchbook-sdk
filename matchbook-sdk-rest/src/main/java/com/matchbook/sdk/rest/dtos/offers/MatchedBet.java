package com.matchbook.sdk.rest.dtos.offers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import com.matchbook.sdk.rest.dtos.RestResponse;

public class MatchedBet extends AbstractMatchedBet implements RestResponse<MatchedBet> {

    private Long id;
    private Long offerId;
    private BigDecimal commission;
    private Date createdAt;

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

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Set<MatchedBet> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return MatchedBet.class.getSimpleName() + " {" +
                "id=" + id +
                ", offerId=" + offerId +
                ", status=" + status +
                ", oddsType=" + oddsType +
                ", currency=" + currency +
                ", odds=" + odds +
                ", stake=" + stake +
                ", potentialProfit=" + potentialProfit +
                ", potentialLiability=" + potentialLiability +
                ", commission=" + commission +
                ", createdAt=" + createdAt +
                "}";
    }

}
