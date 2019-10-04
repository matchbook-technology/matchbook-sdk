package com.matchbook.sdk.rest.dtos.events;

import com.matchbook.sdk.rest.dtos.RestResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Event implements RestResponse {

    private Long id;
    private Long sportId;
    private Long[] categoryIds;
    private String name;
    private EventStatus status;
    private Instant start;
    private boolean inRunning;
    private boolean allowLiveBetting;
    private Double volume;
    private List<Market> markets;
    private List<EventParticipant> eventParticipants;
    private List<MetaTag> metaTags;

    public Event() {
        markets = new ArrayList<>();
        eventParticipants = new ArrayList<>();
        metaTags = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public Long[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Long[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
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

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    public List<EventParticipant> getEventParticipants() {
        return eventParticipants;
    }

    public void setEventParticipants(List<EventParticipant> eventParticipants) {
        this.eventParticipants = eventParticipants;
    }

    public List<MetaTag> getMetaTags() {
        return metaTags;
    }

    public void setMetaTags(List<MetaTag> metaTags) {
        this.metaTags = metaTags;
    }

    @Override
    public String toString() {
        return Event.class.getSimpleName() + " {" +
                "id=" + id +
                ", sportId=" + sportId +
                ", categoryIds=" + Arrays.toString(categoryIds) +
                ", name=" + name +
                ", status=" + status +
                ", start=" + start +
                ", inRunning=" + inRunning +
                ", allowLiveBetting=" + allowLiveBetting +
                ", volume=" + volume +
                ", markets=" + markets +
                ", eventParticipants=" + eventParticipants +
                ", metaTags=" + metaTags +
                "}";
    }

}
