package com.matchbook.sdk.core.clients.rest.dtos;

public abstract class MatchbookPageableRequest implements MatchbookRequest {

    private int offset;
    private int perPage;

    protected MatchbookPageableRequest() {
        offset = 0;
        perPage = 20;
    }

    public int getOffset() {
        return offset;
    }

    public int getPerPage() {
        return perPage;
    }

}
