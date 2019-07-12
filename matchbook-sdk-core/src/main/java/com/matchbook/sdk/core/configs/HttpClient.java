package com.matchbook.sdk.core.configs;

import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;

public interface HttpClient {

    void get(String url, HttpCallback httpCallback) throws MatchbookSDKHttpException;

    void post(String url, String body, HttpCallback httpCallback) throws MatchbookSDKHttpException;

    void delete(String url, HttpCallback httpCallback) throws MatchbookSDKHttpException;

}
