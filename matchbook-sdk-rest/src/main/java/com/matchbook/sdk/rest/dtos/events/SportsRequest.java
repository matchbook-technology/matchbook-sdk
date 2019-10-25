package com.matchbook.sdk.rest.dtos.events;

import java.util.Map;

import com.matchbook.sdk.rest.dtos.PageableRequest;

public class SportsRequest extends PageableRequest {

    private SportsRequest(Init<?> init) {
        super(init);
    }

    @Override
    public String resourcePath() {
        return "lookups/sports";
    }

    @Override
    public Map<String, String> parameters() {
        return pageParameters();
    }

    @Override
    public String toString() {
        return SportsRequest.class.getSimpleName() + " {" +
                "offset=" + offset +
                ", perPage=" + perPage +
                "}";
    }

    private static abstract class Init<T extends Init<T>> extends PageableRequest.Init<T> {

        public SportsRequest build() {
            return new SportsRequest(this);
        }

    }

    public static class Builder extends Init<Builder> {

        @Override
        protected Builder self() {
            return this;
        }
    }

}
