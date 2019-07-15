package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Collections;
import java.util.Map;

import com.matchbook.sdk.core.clients.rest.dtos.RestRequest;

public class AccountRequest implements RestRequest {

    private AccountRequest() {
    }

    @Override
    public String resourcePath() {
        return "account";
    }

    @Override
    public Map<String, String> parameters() {
        return Collections.emptyMap();
    }

    public static class Builder {

        public AccountRequest build() {
            return new AccountRequest();
        }
    }
    
}
