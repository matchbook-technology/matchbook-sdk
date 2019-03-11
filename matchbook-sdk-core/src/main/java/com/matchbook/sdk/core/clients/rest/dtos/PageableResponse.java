package com.matchbook.sdk.core.clients.rest.dtos;

import java.util.List;

public abstract class PageableResponse<T> implements RestResponse {

    protected int total;
    protected int offset;
    protected int perPage;
    protected List<T> content;

    public int getTotal() {
        return total;
    }

    public int getOffset() {
        return offset;
    }

    public int getPerPage() {
        return perPage;
    }

    public List<T> getContent() {
        return content;
    }

}
