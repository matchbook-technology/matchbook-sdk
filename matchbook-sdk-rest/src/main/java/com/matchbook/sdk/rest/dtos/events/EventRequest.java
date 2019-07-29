package com.matchbook.sdk.rest.dtos.events;

import java.util.HashMap;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.prices.AbstractPricesRequest;

public class EventRequest extends AbstractPricesRequest {

    private final Long eventId;
    private final boolean includeEventParticipants;
    private final boolean includePrices;

    private EventRequest(Init<?> init) {
        super(init);

        this.eventId = init.eventId;
        this.includeEventParticipants = init.includeEventParticipants;
        this.includePrices = init.includePrices;
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

    protected static abstract class Init<T extends Init<T>> extends AbstractPricesRequest.Init<T> {
        private final Long eventId;
        private boolean includeEventParticipants;
        private boolean includePrices;

        public Init(Long eventId) {
            this.eventId = eventId;
            includeEventParticipants = false;
            includePrices = false;
        }

        public T includeEventParticipants(boolean includeEventParticipants) {
            this.includeEventParticipants = includeEventParticipants;
            return self();
        }

        public T includePrices(boolean includePrices) {
            this.includePrices = includePrices;
            return self();
        }


        public EventRequest build() {
            return new EventRequest(this);
        }
    }


    public static class Builder extends Init<Builder> {

        public Builder(Long eventId) {
            super(eventId);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}
