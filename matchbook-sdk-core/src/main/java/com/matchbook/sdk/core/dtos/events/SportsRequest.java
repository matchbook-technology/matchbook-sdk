package com.matchbook.sdk.core.dtos.events;

import java.util.Map;

import com.matchbook.sdk.core.dtos.PageableRequest;
import com.matchbook.sdk.core.dtos.PageableRequestBuilder;

public class SportsRequest extends PageableRequest {

    private SportsRequest(SportsRequest.Builder builder) {
        super(builder);
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

    public static class Builder extends PageableRequestBuilder {

        public SportsRequest build() {
            return new SportsRequest(this);
        }

    }

}