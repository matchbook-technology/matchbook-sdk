package com.matchbook.sdk.core.clients.rest.dtos;

import java.util.List;

public abstract class MatchbookPageableResponse<T> implements MatchbookResponse {

    private int total;
    private int offset;
    private int perPage;
    private List<T> content;

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
