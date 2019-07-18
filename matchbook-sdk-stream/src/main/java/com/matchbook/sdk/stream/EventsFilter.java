package com.matchbook.sdk.stream;

import java.util.HashSet;
import java.util.Set;

public class EventsFilter {

    private final Set<Long> sportIds;

    public Set<Long> getSportIds() {
        return sportIds;
    }

    public EventsFilter(EventsFilter.Builder builder) {
        this.sportIds = builder.sportIds;
    }

    public static class Builder {
        private Set<Long> sportIds;

        public Builder() {
            sportIds = new HashSet<>();
        }

        public Builder setSportIds(Set<Long> sportIds) {
            this.sportIds = sportIds;
            return this;
        }

        public EventsFilter build() {
            return new EventsFilter(this);
        }
    }
}
