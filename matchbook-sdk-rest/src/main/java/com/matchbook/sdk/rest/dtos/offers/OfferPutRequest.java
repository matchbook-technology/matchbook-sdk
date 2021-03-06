package com.matchbook.sdk.rest.dtos.offers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.RestRequest;

public class OfferPutRequest implements RestRequest {

    private final Long id;
    private final Double currentOdds;
    private final Double newOdds;
    private final BigDecimal currentStake;
    private final BigDecimal newStake;

    private OfferPutRequest(OfferPutRequest.Builder builder) {
        this.id = builder.id;
        this.currentOdds = builder.currentOdds;
        this.newOdds = builder.newOdds;
        this.currentStake = builder.currentStake;
        this.newStake = builder.newStake;
    }

    public Long getId() {
        return id;
    }

    public Double getCurrentOdds() {
        return currentOdds;
    }

    public Double getNewOdds() {
        return newOdds;
    }

    public BigDecimal getCurrentStake() {
        return currentStake;
    }

    public BigDecimal getNewStake() {
        return newStake;
    }

    @Override
    public String resourcePath() {
        return "v2/offers/" + id;
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return OfferPutRequest.class.getSimpleName() + " {" +
                "id=" + id +
                ", currentOdds=" + currentOdds +
                ", newOdds=" + newOdds +
                ", currentStake=" + currentStake +
                ", newStake=" + newStake +
                "}";
    }

    public static class Builder {

        private final Long id;
        private final Double newOdds;
        private final BigDecimal newStake;

        private Double currentOdds;
        private BigDecimal currentStake;

        public Builder(Long id, Double newOdds, BigDecimal newStake) {
            this.id = id;
            this.newOdds = newOdds;
            this.newStake = newStake;
        }

        public Builder currentOdds(Double currentOdds) {
            this.currentOdds = currentOdds;
            return this;
        }

        public Builder currentStake(BigDecimal currentStake) {
            this.currentStake = currentStake;
            return this;
        }

        public OfferPutRequest build() {
            return new OfferPutRequest(this);
        }

    }

}
