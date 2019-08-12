package com.matchbook.sdk.rest.dtos;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class PageableRequest implements RestRequest {

    protected final Integer offset;
    protected final Integer perPage;

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
        if (Objects.nonNull(offset)) {
            parameters.put("offset", String.valueOf(offset));
        }
        if (Objects.nonNull(perPage)) {
            parameters.put("per-page", String.valueOf(perPage));
        }
        return parameters;
    }

    protected static abstract class Init<T extends Init<T>> {
        private Integer offset;
        private Integer perPage;

        protected abstract T self();

        public T offset(int offset) {
            this.offset = offset;
            return self();
        }

        public T perPage(int perPage) {
            this.perPage = perPage;
            return self();
        }
    }


    public static class Builder extends Init<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
    }
}
