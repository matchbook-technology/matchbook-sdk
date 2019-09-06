package com.matchbook.sdk.rest.dtos;

public abstract class PageableResponse<T> implements RestResponse<T> {

    protected int total;
    protected int offset;
    protected int perPage;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

}
