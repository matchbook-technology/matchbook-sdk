package com.matchbook.sdk.clients.rest.dtos.events;

import com.matchbook.sdk.clients.rest.dtos.MatchbookRequest;

public class RunnerRequest implements MatchbookRequest {

    private Long id;
    private Long eventId;
    private Long marketId;

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

}
