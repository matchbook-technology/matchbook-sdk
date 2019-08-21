package com.matchbook.sdk.rest.dtos.events;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.matchbook.sdk.rest.dtos.prices.PageablePricesRequest;

public class EventsRequest extends PageablePricesRequest {

    private final Instant after;
    private final Instant before;
    private final Set<Long> sportIds;
    private final Set<Long> categoryIds;
    private final Set<Long> eventIds;
    private final Set<EventStatus> statuses;
    private final boolean includeEventParticipants;
    private final boolean includePrices;

    private EventsRequest(Init<?> init) {
        super(init);

        this.after = init.after;
        this.before = init.before;
        this.sportIds = init.sportIds;
        this.categoryIds = init.categoryIds;
        this.eventIds = init.eventIds;
        this.statuses = init.statuses;
        this.includeEventParticipants = init.includeEventParticipants;
        this.includePrices = init.includePrices;
    }

    public Instant getAfter() {
        return after;
    }

    public Instant getBefore() {
        return before;
    }

    public Set<Long> getSportIds() {
        return sportIds;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public Set<Long> getEventIds() {
        return eventIds;
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

    @Override
    public String resourcePath() {
        return "events";
    }

    @Override
    public Map<String, String> parameters() {
        Map<String, String> parameters = new HashMap<>();

        if (Objects.nonNull(after)) {
            parameters.put("after", after.toString());
        }
        if (Objects.nonNull(before)) {
            parameters.put("before", before.toString());
        }
        if (Objects.nonNull(eventIds) && !eventIds.isEmpty()) {
            List<String> ids = eventIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("ids", String.join(",", ids));
        }
        if (Objects.nonNull(categoryIds) && !categoryIds.isEmpty()) {
            List<String> ids = categoryIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("category-ids", String.join(",", ids));
        }
        if (Objects.nonNull(sportIds) && !sportIds.isEmpty()) {
            List<String> ids = sportIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            parameters.put("sport-ids", String.join(",", ids));
        }
        if (Objects.nonNull(statuses) && !statuses.isEmpty()) {
            List<String> states = statuses.stream()
                    .map(EventStatus::name)
                    .collect(Collectors.toList());
            parameters.put("states", String.join(",", states));
        }
        parameters.put("include-event-participants", String.valueOf(includeEventParticipants));
        if (includePrices) {
            parameters.put("include-prices", "true");
        }
        parameters.putAll(pricesParameters());

        return parameters;
    }

    @Override
    public String toString() {
        return EventsRequest.class.getSimpleName() + " {" +
                "after=" + after +
                ", before=" + before +
                ", sportIds=" + sportIds +
                ", categoryIds=" + categoryIds +
                ", eventIds=" + eventIds +
                ", statuses=" + statuses +
                ", includeEventParticipants=" + includeEventParticipants +
                ", includePrices=" + includePrices +
                (includePrices ? (
                        ", oddsType=" + oddsType +
                        ", exchangeType=" + exchangeType +
                        ", side=" + side +
                        ", currency=" + currency +
                        ", minimumLiquidity=" + minimumLiquidity +
                        ", priceMode=" + priceMode
                ) : "") +
                ", offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    private static abstract class Init<T extends Init<T>> extends PageablePricesRequest.Init<T> {
        private Instant after;
        private Instant before;
        private Set<Long> sportIds;
        private Set<Long> categoryIds;
        private Set<Long> eventIds;
        private Set<EventStatus> statuses;
        private boolean includeEventParticipants;
        private boolean includePrices;

        private Init() {
            includeEventParticipants = false;
            includePrices = false;
        }

        public T after(Instant after) {
            this.after = after;
            return self();
        }

        public T before(Instant before) {
            this.before = before;
            return self();
        }

        public T sportIds(Set<Long> sportIds) {
            this.sportIds = sportIds;
            return self();
        }

        public T categoryIds(Set<Long> categoryIds) {
            this.categoryIds = categoryIds;
            return self();
        }

        public T eventIds(Set<Long> eventIds) {
            this.eventIds = eventIds;
            return self();
        }

        public T statuses(Set<EventStatus> statuses) {
            this.statuses = statuses;
            return self();
        }

        public T includeEventParticipants(boolean includeEventParticipants) {
            this.includeEventParticipants = includeEventParticipants;
            return self();
        }

        public T includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return self();
        }

        public EventsRequest build() {
            return new EventsRequest(this);
        }
    }

    public static class Builder extends Init<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
    }

}
