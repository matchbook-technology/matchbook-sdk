package com.matchbook.sdk.core.clients.rest.dtos.events;

import java.util.Set;

import com.matchbook.sdk.core.clients.rest.dtos.prices.AbstractPricesRequest;

public class MarketsRequest extends AbstractPricesRequest {

    private Long eventId;
    private Set<MarketType> types;
    private Set<MarketStatus> statuses;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Set<MarketType> getTypes() {
        return types;
    }

    public void setTypes(Set<MarketType> types) {
        this.types = types;
    }

    public Set<MarketStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(Set<MarketStatus> statuses) {
        this.statuses = statuses;
    }

}
