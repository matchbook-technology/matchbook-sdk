package com.matchbook.sdk.core.clients.rest.dtos.events;

import java.util.HashMap;
import java.util.Map;

import com.matchbook.sdk.core.clients.rest.dtos.prices.BasePricesRequest;
import com.matchbook.sdk.core.clients.rest.dtos.prices.BasePricesRequestBuilder;

public class EventRequest extends BasePricesRequest {

    private final Long eventId;
    private final boolean includeEventParticipants;
    private final boolean includePrices;

    private EventRequest(EventRequest.Builder builder) {
        super(builder);

        this.eventId = builder.eventId;
        this.includeEventParticipants = builder.includeEventParticipants;
        this.includePrices = builder.includePrices;
    }

    public Long getEventId() {
        return eventId;
    }

    public boolean includeEventParticipants() {
        return includeEventParticipants;
    }

    public boolean includePrices() {
        return includePrices;
    }

    @Override
    public String resourcePath() {
        return "events/" + eventId;
    }

    @Override
    public Map<String, String> parameters() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("include-event-participants", String.valueOf(includeEventParticipants));
        if (includePrices) {
            parameters.put("include-prices", "true");
            parameters.putAll(pricesParameters());
        }
        return parameters;
    }

    @Override
    public String toString() {
        return EventRequest.class.getSimpleName() + " {" +
                "eventId=" + eventId +
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
                "}";
    }

    public static class Builder extends BasePricesRequestBuilder {

        private final Long eventId;
        private boolean includeEventParticipants;
        private boolean includePrices;

        public Builder(Long eventId) {
            this.eventId = eventId;
            includeEventParticipants = false;
            includePrices = false;
        }

        public Builder includeEventParticipants(boolean includeEventParticipants) {
            this.includeEventParticipants = includeEventParticipants;
            return this;
        }

        public Builder includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return this;
        }

        public EventRequest build() {
            return new EventRequest(this);
        }
    }

}
