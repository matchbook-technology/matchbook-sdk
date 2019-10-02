package com.matchbook.sdk.rest.dtos;

import java.util.ArrayList;
import java.util.List;

public abstract class PageableResponse<T> implements RestResponse {

    protected int total;
    protected int offset;
    protected int perPage;
    protected List<T> items;

    protected PageableResponse() {
        items = new ArrayList<>();
    }

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

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
