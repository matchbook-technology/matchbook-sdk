package com.matchbook.sdk.core.clients.rest.dtos;

import java.util.List;

public abstract class PageableResponse<T> implements RestResponse<T> {

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

    @Override
    public List<T> getContent() {
        return content;
    }

}
