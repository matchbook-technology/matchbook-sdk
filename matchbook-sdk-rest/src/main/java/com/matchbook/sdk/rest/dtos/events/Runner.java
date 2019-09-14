package com.matchbook.sdk.rest.dtos.events;

import com.matchbook.sdk.rest.dtos.RestResponse;
import com.matchbook.sdk.rest.dtos.prices.Price;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Runner implements RestResponse {

    private Long id;
    private Long eventId;
    private Long marketId;
    private Long eventParticipantId;
    private String name;
    private RunnerStatus status;
    private boolean withdrawn;
    private Double handicap;
    private BigDecimal volume;
    private List<Price> prices;

    public Runner() {
        prices = new ArrayList<>();
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

    public Long getEventParticipantId() {
        return eventParticipantId;
    }

    public void setEventParticipantId(Long eventParticipantId) {
        this.eventParticipantId = eventParticipantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RunnerStatus getStatus() {
        return status;
    }

    public void setStatus(RunnerStatus status) {
        this.status = status;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

    public Double getHandicap() {
        return handicap;
    }

    public void setHandicap(Double handicap) {
        this.handicap = handicap;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return Runner.class.getSimpleName() + " {" +
                "id=" + id +
                ", eventId=" + eventId +
                ", marketId=" + marketId +
                ", eventParticipantId=" + eventParticipantId +
                ", name=" + name +
                ", status=" + status +
                ", withdrawn=" + withdrawn +
                ", handicap=" + handicap +
                ", volume=" + volume +
                ", prices=" + prices +
                "}";
    }

}
