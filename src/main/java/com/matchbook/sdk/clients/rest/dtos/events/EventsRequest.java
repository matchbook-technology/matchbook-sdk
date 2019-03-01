package com.matchbook.sdk.clients.rest.dtos.events;

import java.time.Instant;
import java.util.Set;

import com.matchbook.sdk.clients.rest.dtos.MatchbookPageableRequest;

public class EventsRequest extends MatchbookPageableRequest {

    private Instant after;
    private Instant before;
    private Set<Long> ids;
    private Set<Long> sportIds;
    private Set<Long> categoryIds;
    private Set<EventStatus> statuses;
    private boolean includeEventParticipants;

    public Instant getAfter() {
        return after;
    }

    public void setAfter(Instant after) {
        this.after = after;
    }

    public Instant getBefore() {
        return before;
    }

    public void setBefore(Instant before) {
        this.before = before;
    }

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
    }

    public Set<Long> getSportIds() {
        return sportIds;
    }

    public void setSportIds(Set<Long> sportIds) {
        this.sportIds = sportIds;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Set<EventStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(Set<EventStatus> statuses) {
        this.statuses = statuses;
    }

    public boolean isIncludeEventParticipants() {
        return includeEventParticipants;
    }

    public void setIncludeEventParticipants(boolean includeEventParticipants) {
        this.includeEventParticipants = includeEventParticipants;
    }

}
