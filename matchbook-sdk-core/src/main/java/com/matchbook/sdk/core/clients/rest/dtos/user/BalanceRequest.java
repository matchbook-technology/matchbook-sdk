package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Map;

import com.matchbook.sdk.core.clients.rest.dtos.PageableRequest;
import com.matchbook.sdk.core.clients.rest.dtos.PageableRequestBuilder;

public class BalanceRequest extends PageableRequest {

    private BalanceRequest(BalanceRequest.Builder builder) {
        super(builder);
    }

    @Override
    public String resourcePath() {
        return "/rest/account/balance";
    }

    @Override
    public Map<String, String> parameters() {
        return null;
    }

    public static class Builder extends PageableRequestBuilder {

        public BalanceRequest build() {
            return new BalanceRequest(this);
        }

    }
}