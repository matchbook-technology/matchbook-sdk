package com.matchbook.sdk.rest.dtos;

import java.util.HashMap;
import java.util.Map;

public abstract class PageableRequest implements RestRequest {

    protected final int offset;
    protected final int perPage;

    protected PageableRequest(Init<?> init) {
        this.offset = init.offset;
        this.perPage = init.perPage;
    }

    public int getOffset() {
        return offset;
    }

    public int getPerPage() {
        return perPage;
    }

    protected Map<String, String> pageParameters() {
        Map<String, String> parameters = new HashMap<>(2);
        parameters.put("offset", String.valueOf(offset));
        parameters.put("per-page", String.valueOf(perPage));
        return parameters;
    }

    protected static abstract class Init<T extends Init<T>> {

        private int offset;
        private int perPage;

        protected abstract T self();

        protected Init() {
            offset = 0;
            perPage = 20;
        }

        public T offset(int offset) {
            this.offset = offset;
            return self();
        }

        public T perPage(int perPage) {
            this.perPage = perPage;
            return self();
        }

    }

}
