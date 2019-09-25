package com.matchbook.sdk.rest.configs;

import java.io.Closeable;

import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;

public interface HttpClient extends Closeable {

    void get(String url, HttpCallback httpCallback) throws MatchbookSDKHttpException;

    void post(String url, String body, HttpCallback httpCallback) throws MatchbookSDKHttpException;

    void put(String url, String body, HttpCallback httpCallback) throws MatchbookSDKHttpException;

    void delete(String url, HttpCallback httpCallback) throws MatchbookSDKHttpException;

}
