package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookRequest;

public class EventRequest implements MatchbookRequest {

    private Long id;
    private boolean includeEventParticipants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIncludeEventParticipants() {
        return includeEventParticipants;
    }

    public void setIncludeEventParticipants(boolean includeEventParticipants) {
        this.includeEventParticipants = includeEventParticipants;
    }

}
