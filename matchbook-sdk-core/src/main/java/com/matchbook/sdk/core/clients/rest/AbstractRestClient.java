package com.matchbook.sdk.core.clients.rest;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.errors.Errors;
import com.matchbook.sdk.core.exceptions.ErrorCode;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHTTPException;
import com.squareup.okhttp.Response;

public abstract class AbstractRestClient {

    private final ObjectReader errorReader;

    public AbstractRestClient(ObjectMapper objectMapper) {
        this.errorReader = objectMapper.readerFor(Errors.class);
    }

    protected <T> void errorHandler(Response response, StreamObserver<T> observer) {
        if (Objects.nonNull(response.body())) {
            try {
                Errors errors = errorReader.readValue(response.body().byteStream());
                if (Objects.nonNull(errors)) {
                    String errorMessages = getErrorMessages(errors);
                    if (errorMessages.contains("cannot login")) {
                        publishUnauthenticatedError(observer);
                        return;
                    }
                }
            } catch (IOException e) {
                publishHTTPError(response, observer);
            }
        }
        publishHTTPError(response, observer);
    }

    private <T> void publishHTTPError(Response response, StreamObserver<T> observer) {
        observer.onError(new MatchbookSDKHTTPException("Unexpected HTTP code " + response));
    }

    private <T> void publishUnauthenticatedError(StreamObserver<T> observer) {
        observer.onError(new MatchbookSDKHTTPException("Incorrect username or password", ErrorCode.UNAUTHENTICATED));
    }

    private String getErrorMessages(Errors errors) {
        return errors.getErrors().stream()
                .flatMap(error -> error.getMessages().stream())
                .map(String::toLowerCase)
                .collect(Collectors.joining("; "));
    }
}
