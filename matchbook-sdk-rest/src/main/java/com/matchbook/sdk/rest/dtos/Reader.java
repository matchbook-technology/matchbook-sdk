package com.matchbook.sdk.rest.dtos;

import com.matchbook.sdk.rest.configs.Parser;

public interface Reader<R extends RestResponse<T>, T> {

    boolean hasNext();

    T readNext(Parser parser);

}
