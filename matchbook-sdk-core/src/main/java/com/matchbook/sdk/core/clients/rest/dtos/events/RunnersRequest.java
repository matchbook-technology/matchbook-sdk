package com.matchbook.sdk.core.clients.rest.dtos.events;

import java.util.Set;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookPageableRequest;

public class RunnersRequest extends MatchbookPageableRequest {

    private Long eventId;
    private Long marketId;
    private Set<RunnerStatus> statuses;
    private boolean includeWithdrawn;

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

    public Set<RunnerStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(Set<RunnerStatus> statuses) {
        this.statuses = statuses;
    }

    public boolean isIncludeWithdrawn() {
        return includeWithdrawn;
    }

    public void setIncludeWithdrawn(boolean includeWithdrawn) {
        this.includeWithdrawn = includeWithdrawn;
    }

}
