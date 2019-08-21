package com.matchbook.sdk.rest.configs.wrappers;

import java.io.IOException;
import java.util.Objects;

import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.rest.configs.HttpCallback2;
import com.matchbook.sdk.rest.dtos.Reader;
import com.matchbook.sdk.rest.dtos.RestResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

class RequestCallback2<T, R extends RestResponse<T>> implements Callback {

    private final HttpCallback2<T, R> httpCallback;
    private final Reader<T, R> reader;

    RequestCallback2(HttpCallback2<T, R> httpCallback, Reader<T, R> reader) {
        this.httpCallback = httpCallback;
        this.reader = reader;
    }

    @Override
    public void onResponse(Call call, Response response) {
        try (ResponseBody responseBody = response.body()) {
            if (response.isSuccessful() && Objects.nonNull(responseBody)) {
                httpCallback.onResponse(responseBody.byteStream(), reader);
            } else {
                MatchbookSDKHttpException matchbookException = getExceptionForResponse(response);
                httpCallback.onFailure(matchbookException);
            }
        }
    }

    @Override
    public void onFailure(Call call, IOException exception) {
        MatchbookSDKHttpException matchbookException = new MatchbookSDKHttpException(exception.getMessage(), exception);
        httpCallback.onFailure(matchbookException);
    }

    private MatchbookSDKHttpException getExceptionForResponse(Response response) {
        ResponseBody responseBody = response.body();
        if (Objects.nonNull(responseBody)) {
            try {
                if (isAuthenticationErrorPresent(responseBody)) {
                    return newUnauthenticatedException();
                }
            } catch (IOException e) {
                return newHTTPException(response.code());
            }
        }
        return newHTTPException(response.code());
    }

    private boolean isAuthenticationErrorPresent(ResponseBody body) throws IOException {
        return new String(body.bytes()).toLowerCase().contains("cannot login");
    }

    private MatchbookSDKHttpException newHTTPException(int responseCode) {
        return new MatchbookSDKHttpException("Unexpected HTTP code " + responseCode, ErrorType.HTTP);
    }

    private MatchbookSDKHttpException newUnauthenticatedException() {
        return new MatchbookSDKHttpException("Incorrect username or password", ErrorType.UNAUTHENTICATED);
    }

}
