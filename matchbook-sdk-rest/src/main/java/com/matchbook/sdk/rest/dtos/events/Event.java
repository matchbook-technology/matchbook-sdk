package com.matchbook.sdk.rest.dtos.events;

import com.matchbook.sdk.rest.dtos.RestResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Event implements RestResponse<Event> {

    private Long id;
    private Long sportId;
    private Long[] categoryIds;
    private String name;
    private EventStatus status;
    private Instant start;
    private boolean inRunning;
    private boolean allowLiveBetting;
    private List<Market> markets;
    private List<EventParticipant> eventParticipants;

    public Event() {
        markets = new ArrayList<>();
        eventParticipants = new ArrayList<>();
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

    @Override
    public Set<Event> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return Event.class.getSimpleName() + " {" +
                "id=" + id +
                ", sportId=" + sportId +
                ", categoryIds=" + categoryIds +
                ", name=" + name +
                ", status=" + status +
                ", start=" + start +
                ", inRunning=" + inRunning +
                ", allowLiveBetting=" + allowLiveBetting +
                ", markets=" + markets +
                ", eventParticipants=" + eventParticipants +
                "}";
    }

}
