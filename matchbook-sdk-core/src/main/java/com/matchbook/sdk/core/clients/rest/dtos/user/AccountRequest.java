package com.matchbook.sdk.core.clients.rest.dtos.user;

import java.util.Map;

import com.matchbook.sdk.core.clients.rest.dtos.PageableRequest;
import com.matchbook.sdk.core.clients.rest.dtos.PageableRequestBuilder;

public class AccountRequest extends PageableRequest {

    private AccountRequest(AccountRequest.Builder builder) {
        super(builder);
    }

    @Override
    public String resourcePath() {
        return "/rest/account";
    }

    @Override
    public Map<String, String> parameters() {
        return null;
    }

    public static class Builder extends PageableRequestBuilder {

        public AccountRequest build() {
            return new AccountRequest(this);
        }

    }
}
