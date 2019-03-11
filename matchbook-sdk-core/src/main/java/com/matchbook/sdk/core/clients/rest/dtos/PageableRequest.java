package com.matchbook.sdk.core.clients.rest.dtos;

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

}
