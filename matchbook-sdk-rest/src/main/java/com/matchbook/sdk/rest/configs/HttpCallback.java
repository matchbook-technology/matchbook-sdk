package com.matchbook.sdk.rest.configs;

import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;

import java.io.InputStream;

public interface HttpCallback {

    void onResponse(InputStream inputStream);

    void onFailure(MatchbookSDKHttpException exception);

}
