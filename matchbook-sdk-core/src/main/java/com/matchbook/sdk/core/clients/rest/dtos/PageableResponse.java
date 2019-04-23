package com.matchbook.sdk.core.clients.rest.dtos;

public abstract class PageableResponse<T> implements RestResponse<T> {

    protected int total;
    protected int offset;
    protected int perPage;

    public int getTotal() {
        return total;
    }

    public int getOffset() {
        return offset;
    }

    public int getPerPage() {
        return perPage;
    }

}
