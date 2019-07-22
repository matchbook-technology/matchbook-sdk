package com.matchbook.sdk.core.dtos;

import java.util.HashMap;
import java.util.Map;

public abstract class PageableRequest implements RestRequest {

    protected final int offset;
    protected final int perPage;

    protected <B extends PageableRequestBuilder> PageableRequest(B builder) {
        this.offset = builder.offset;
        this.perPage = builder.perPage;
    }

    public int getOffset() {
        return offset;
    }

    public int getPerPage() {
        return perPage;
    }

    protected Map<String, String> pageParameters() {
        Map<String, String> parameters = new HashMap<>(2);
        parameters.put("offset", String.valueOf(offset));
        parameters.put("per-page", String.valueOf(perPage));
        return parameters;
    }

}
