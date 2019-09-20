package com.matchbook.sdk.rest.dtos.events;

import java.util.ArrayList;
import java.util.List;

import com.matchbook.sdk.rest.dtos.RestResponse;

public class Market implements RestResponse {

    private Long id;
    private Long eventId;
    private String name;
    private MarketStatus status;
    private MarketType marketType;
    private boolean inRunning;
    private boolean allowLiveBetting;
    private Double handicap;
    private Double volume;
    private Double backOverround;
    private Double layOverround;
    private Integer winners;
    private List<Runner> runners;

    public Market() {
        runners = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MarketStatus getStatus() {
        return status;
    }

    public void setStatus(MarketStatus status) {
        this.status = status;
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public void setMarketType(MarketType marketType) {
        this.marketType = marketType;
    }

    public boolean isInRunning() {
        return inRunning;
    }

    public void setInRunning(boolean inRunning) {
        this.inRunning = inRunning;
    }

    public boolean isAllowLiveBetting() {
        return allowLiveBetting;
    }

    public void setAllowLiveBetting(boolean allowLiveBetting) {
        this.allowLiveBetting = allowLiveBetting;
    }

    public Double getHandicap() {
        return handicap;
    }

    public void setHandicap(Double handicap) {
        this.handicap = handicap;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getBackOverround() {
        return backOverround;
    }

    public void setBackOverround(Double backOverround) {
        this.backOverround = backOverround;
    }

    public Double getLayOverround() {
        return layOverround;
    }

    public void setLayOverround(Double layOverround) {
        this.layOverround = layOverround;
    }

    public Integer getWinners() {
        return winners;
    }

    public void setWinners(Integer winners) {
        this.winners = winners;
    }

    public List<Runner> getRunners() {
        return runners;
    }

    public void setRunners(List<Runner> runners) {
        this.runners = runners;
    }

    @Override
    public String toString() {
        return Market.class.getSimpleName() + " {" +
                "id=" + id +
                ", eventId=" + eventId +
                ", name=" + name +
                ", status=" + status +
                ", marketType=" + marketType +
                ", inRunning=" + inRunning +
                ", allowLiveBetting=" + allowLiveBetting +
                ", handicap=" + handicap +
                ", volume=" + volume +
                ", backOverround=" + backOverround +
                ", layOverround=" + layOverround +
                ", winners=" + winners +
                ", runners=" + runners +
                "}";
    }

}
