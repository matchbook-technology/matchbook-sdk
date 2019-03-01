package com.matchbook.sdk.clients.rest.dtos.events;

import java.util.ArrayList;
import java.util.List;

public class Market {

    private Long id;
    private Long eventId;
    private String name;
    private MarketStatus status;
    private MarketType type;
    private boolean inRunning;
    private boolean allowLiveBetting;
    private Double value;
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

    public MarketType getType() {
        return type;
    }

    public void setType(MarketType type) {
        this.type = type;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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

}
