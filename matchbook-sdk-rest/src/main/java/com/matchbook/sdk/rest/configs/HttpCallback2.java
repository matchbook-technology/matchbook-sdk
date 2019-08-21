package com.matchbook.sdk.rest.configs;

import java.io.InputStream;

import com.matchbook.sdk.rest.dtos.Reader;
import com.matchbook.sdk.rest.dtos.RestResponse;

public interface HttpCallback2<T, R extends RestResponse<T>> extends HttpCallback {

    void onResponse(InputStream inputStream, Reader<T, R> reader);

    @Deprecated
    void onResponse(InputStream inputStream);

}
