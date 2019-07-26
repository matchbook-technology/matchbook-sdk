package com.matchbook.sdk.rest.dtos;

public abstract class PageableRequestBuilder {

    private static final int DEFAULT_OFFSET = 0;
    private static final int DEFAULT_PER_PAGE = 20;

    protected int offset = DEFAULT_OFFSET;
    protected int perPage = DEFAULT_PER_PAGE;

    public PageableRequestBuilder offset(int offset) {
        this.offset = offset;
        return this;
    }

    public PageableRequestBuilder perPage(int perPage) {
        this.perPage = perPage;
        return this;
    }

}
