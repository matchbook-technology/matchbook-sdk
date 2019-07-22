package com.matchbook.sdk.core.dtos.user;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.core.dtos.RestRequest;

public class BalanceRequest implements RestRequest {

    private BalanceRequest() {
        // not visible
    }

    @Override
    public String resourcePath() {
        return "account/balance";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return BalanceRequest.class.getSimpleName();
    }

    public static class Builder {
        public BalanceRequest build() {
            return new BalanceRequest();
        }
    }
}
