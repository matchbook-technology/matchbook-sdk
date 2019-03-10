package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.prices.PageablePricesRequest;
import com.matchbook.sdk.core.clients.rest.dtos.prices.PageablePricesRequestBuilder;

import java.time.Instant;
import java.util.Set;

public class EventsRequest extends PageablePricesRequest {

    private final Instant after;
    private final Instant before;
    private final Set<Long> eventIds;
    private final Set<Long> sportIds;
    private final Set<Long> categoryIds;
    private final Set<EventStatus> statuses;
    private final boolean includeEventParticipants;
    private final boolean includePrices;

    private EventsRequest(EventsRequest.Builder builder) {
        super(builder);

        this.after = builder.after;
        this.before = builder.before;
        this.eventIds = builder.eventIds;
        this.sportIds = builder.sportIds;
        this.categoryIds = builder.categoryIds;
        this.statuses = builder.statuses;
        this.includeEventParticipants = builder.includeEventParticipants;
        this.includePrices = builder.includePrices;
    }

    public Instant getAfter() {
        return after;
    }

    public Instant getBefore() {
        return before;
    }

    public Set<Long> getIds() {
        return eventIds;
    }

    public Set<Long> getSportIds() {
        return sportIds;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public Set<EventStatus> getStatuses() {
        return statuses;
    }

    public boolean includeEventParticipants() {
        return includeEventParticipants;
    }

    public boolean includePrices() {
        return includePrices;
    }

    public static class Builder extends PageablePricesRequestBuilder {

        private Instant after;
        private Instant before;
        private Set<Long> eventIds;
        private Set<Long> sportIds;
        private Set<Long> categoryIds;
        private Set<EventStatus> statuses;
        private boolean includeEventParticipants;
        private boolean includePrices;

        public Builder() {
            includeEventParticipants = false;
            includePrices = false;
        }

        public Builder after(Instant after) {
            this.after = after;
            return this;
        }

        public Builder before(Instant before) {
            this.before = before;
            return this;
        }

        public Builder eventIds(Set<Long> eventIds) {
            this.eventIds = eventIds;
            return this;
        }

        public Builder sportIds(Set<Long> sportIds) {
            this.sportIds = sportIds;
            return this;
        }

        public Builder categoryIds(Set<Long> categoryIds) {
            this.categoryIds = categoryIds;
            return this;
        }

        public Builder statuses(Set<EventStatus> statuses) {
            this.statuses = statuses;
            return this;
        }

        public Builder includeEventParticipants(boolean includeEventParticipants) {
            this.includeEventParticipants = includeEventParticipants;
            return this;
        }

        public Builder includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return this;
        }

        public EventsRequest build() {
            return new EventsRequest(this);
        }
    }

}
