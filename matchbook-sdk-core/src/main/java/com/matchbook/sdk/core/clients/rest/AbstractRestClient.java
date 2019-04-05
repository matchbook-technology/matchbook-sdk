package com.matchbook.sdk.core.clients.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.matchbook.sdk.core.configs.ClientConfig;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.RestRequest;
import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;
import com.matchbook.sdk.core.clients.rest.dtos.errors.Errors;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
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
    private static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    private static final String JSON_TYPE = "application/json";

    private final ClientConnectionManager clientConnectionManager;
    private final Map<Class<?>, ObjectWriter> objectWriters;
    private final Map<Class<?>, ObjectReader> objectReaders;
    private final ObjectReader errorReader;
    private final MediaType jsonMediaType;

    protected AbstractRestClient(ClientConnectionManager clientConnectionManager) {
        this.clientConnectionManager = clientConnectionManager;
        this.errorReader = clientConnectionManager.getObjectMapper().readerFor(Errors.class);
        this.jsonMediaType = MediaType.parse(JSON_TYPE);

        objectWriters = new HashMap<>();
        objectReaders = new HashMap<>();
    }

    protected ClientConfig getClientConfig() {
        return clientConnectionManager.getClientConfig();
    }

    protected <R extends RestRequest, T> void postRequest(String url, R request, StreamObserver<T> observer, Class<T> observedResource) {
        try {
            ObjectWriter objectWriter = getObjectWriter(request.getClass());
            String requestBody = objectWriter.writeValueAsString(request);
            Request httpRequest = buildJsonRequest(url)
                    .post(RequestBody.create(jsonMediaType, requestBody))
                    .build();

            ObjectReader objectReader = getObjectReader(observedResource);
            sendHttpRequest(httpRequest, observer, objectReader);
        } catch (JsonProcessingException e) {
            observer.onError(new MatchbookSDKHTTPException((e.getCause())));
        }
    }

    protected <R extends RestRequest, T> void getRequest(String url, R request, StreamObserver<T> observer, Class<T> observedResource) {
        String requestUrl = buildUrl(url, request);
        Request httpRequest = buildJsonRequest(requestUrl)
                .get()
                .build();

        ObjectReader objectReader = getObjectReader(observedResource);
        sendHttpRequest(httpRequest, observer, objectReader);
    }

    private ObjectWriter getObjectWriter(Class<?> clazz) {
        return objectWriters.computeIfAbsent(clazz, c -> clientConnectionManager.getObjectMapper().writerFor(c));
    }

    private ObjectReader getObjectReader(Class<?> clazz) {
        return objectReaders.computeIfAbsent(clazz, c -> clientConnectionManager.getObjectMapper().readerFor(c));
    }

    private <R extends RestRequest> String buildUrl(String baseUrl, R request) {
        List<String> queryParams = request.parameters().entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.toList());
        return baseUrl + (queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams));
    }

    private Request.Builder buildJsonRequest(String url) {
        return new Request.Builder()
                .addHeader(HTTP_HEADER_CONTENT_TYPE, jsonMediaType.toString())
                .addHeader(HTTP_HEADER_ACCEPT, jsonMediaType.toString())
                .url(url);
    }

    private <T> void sendHttpRequest(Request request, StreamObserver<T> observer, ObjectReader objectReader) {
        clientConnectionManager.getHttpClient()
                .newCall(request)
                .enqueue(new RestCallback<>(observer, objectReader));
    }

    private class RestCallback<T> implements Callback {

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
                    .anyMatch(message -> message.toLowerCase().contains("cannot login"));
        }

        private MatchbookSDKHTTPException newHTTPException(Response response) {
            return new MatchbookSDKHTTPException("Unexpected HTTP code " + response, ErrorCode.HTTP_ERROR);
        }

        private MatchbookSDKHTTPException newUnauthenticatedException() {
            return new MatchbookSDKHTTPException("Incorrect username or password", ErrorCode.UNAUTHENTICATED);
        }

    }

}
