package com.matchbook.sdk.rest.configs;

import java.io.IOException;
import java.io.InputStream;

public interface HttpCallback {

    void onSuccessfulResponse(InputStream responseInputStream);

    void onFailedResponse(InputStream responseInputStream, int responseCode);

    void onException(IOException ioException);

}
