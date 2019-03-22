package com.matchbook.sdk.core.clients.rest;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;
import com.matchbook.sdk.core.clients.rest.dtos.errors.Errors;
import com.matchbook.sdk.core.exceptions.ErrorCode;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHTTPException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

abstract class AbstractRestClient {

    private static final String HTTP_HEADER_ACCEPT = "Accept";
    private static final String JSON_TYPE = "application/json";

    private final MediaType jsonMediaType;
    private final ObjectReader errorReader;

    protected AbstractRestClient(ObjectMapper objectMapper) {
        this.jsonMediaType = MediaType.parse(JSON_TYPE);
        this.errorReader = objectMapper.readerFor(Errors.class);
    }

    protected Request buildRequest(String url, String body) {
        RequestBody requestBody = RequestBody.create(jsonMediaType, body);
        return new Request.Builder()
                .url(url)
                .addHeader(HTTP_HEADER_ACCEPT, jsonMediaType.toString())
                .post(requestBody)
                .build();
    }

    protected class RestCallback<T> implements Callback {

        private final StreamObserver<T> observer;
        private final ObjectReader objectReader;

        private RestCallback(StreamObserver<T> observer, ObjectReader objectReader) {
            this.observer = observer;
            this.objectReader = objectReader;
        }

        @Override
        public void onResponse(Response response) throws IOException {
            try (ResponseBody responseBody = response.body()) {
                if (response.isSuccessful()) {
                    RestResponse<T> restResponse = objectReader.readValue(responseBody.byteStream());
                    restResponse.getContent().forEach(observer::onNext);
                    observer.onCompleted();
                } else {
                    MatchbookSDKHTTPException matchbookException = getExceptionForResponse(response);
                    observer.onError(matchbookException);
                }
            }
        }

        @Override
        public void onFailure(Request request, IOException e) {
            MatchbookSDKHTTPException matchbookException = new MatchbookSDKHTTPException(e.getMessage(), e);
            observer.onError(matchbookException);
        }

        private MatchbookSDKHTTPException getExceptionForResponse(Response response) {
            if (Objects.nonNull(response.body())) {
                try {
                    Errors errors = errorReader.readValue(response.body().byteStream());
                    if (Objects.nonNull(errors) && isAuthenticationErrorPresent(errors)) {
                        return newUnauthenticatedException();
                    }
                } catch (IOException e) {
                    return newHTTPException(response);
                }
            }
            return newHTTPException(response);
        }

        private boolean isAuthenticationErrorPresent(Errors errors) {
            return errors.getErrors().stream()
                    .flatMap(error -> error.getMessages().stream())
                    .anyMatch("cannot login"::equalsIgnoreCase);
        }

        private MatchbookSDKHTTPException newHTTPException(Response response) {
            return new MatchbookSDKHTTPException("Unexpected HTTP code " + response, ErrorCode.HTTP_ERROR);
        }

        private MatchbookSDKHTTPException newUnauthenticatedException() {
            return new MatchbookSDKHTTPException("Incorrect username or password", ErrorCode.UNAUTHENTICATED);
        }

    }

}