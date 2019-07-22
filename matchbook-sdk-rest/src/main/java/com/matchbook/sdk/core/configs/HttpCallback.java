package com.matchbook.sdk.core.configs;

import java.io.InputStream;

import com.matchbook.sdk.common.exceptions.MatchbookSDKHttpException;

public interface HttpCallback {

    void onResponse(InputStream inputStream);

    void onFailure(MatchbookSDKHttpException exception);

}
