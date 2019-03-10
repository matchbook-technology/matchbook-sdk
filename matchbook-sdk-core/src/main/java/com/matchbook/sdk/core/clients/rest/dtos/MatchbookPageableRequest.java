package com.matchbook.sdk.core.clients.rest.dtos;

public abstract class MatchbookPageableRequest implements MatchbookRequest {

    protected final int offset;
    protected final int perPage;

    protected <B extends PageableRequestBuilder> MatchbookPageableRequest(B builder) {
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
