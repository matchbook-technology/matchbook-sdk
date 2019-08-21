package com.matchbook.sdk.rest.configs;

import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.rest.dtos.Reader;
import com.matchbook.sdk.rest.dtos.RestResponse;

public interface HttpClient {

    <T, R extends RestResponse<T>> void get(String url, HttpCallback2<T, R> httpCallback, Reader<T, R> reader)
            throws MatchbookSDKHttpException;

    @Deprecated
    void get(String url, HttpCallback httpCallback) throws MatchbookSDKHttpException;

    <T, R extends RestResponse<T>> void post(String url, String body, HttpCallback2<T, R> httpCallback, Reader<T, R> reader)
            throws MatchbookSDKHttpException;

    @Deprecated
    void post(String url, String body, HttpCallback httpCallback) throws MatchbookSDKHttpException;

    <T, R extends RestResponse<T>> void put(String url, String body, HttpCallback2<T, R> httpCallback, Reader<T, R> reader)
            throws MatchbookSDKHttpException;

    @Deprecated
    void put(String url, String body, HttpCallback httpCallback) throws MatchbookSDKHttpException;

    <T, R extends RestResponse<T>> void delete(String url, HttpCallback2<T, R> httpCallback, Reader<T, R> reader)
            throws MatchbookSDKHttpException;

    @Deprecated
    void delete(String url, HttpCallback httpCallback) throws MatchbookSDKHttpException;

}
