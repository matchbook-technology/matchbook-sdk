package com.matchbook.sdk.rest.dtos.user;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.rest.dtos.RestRequest;

public class AccountRequest implements RestRequest {

    private AccountRequest() {
        // not visible
    }

    @Override
    public String resourcePath() {
        return "account";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return AccountRequest.class.getSimpleName();
    }

    public static class Builder {
        public AccountRequest build() {
            return new AccountRequest();
        }
    }
}
