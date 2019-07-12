package com.matchbook.sdk.core.clients.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.RestRequest;
import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;
import com.matchbook.sdk.core.configs.ConnectionManager;
import com.matchbook.sdk.core.configs.HttpCallback;
import com.matchbook.sdk.core.configs.Serializer;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;

abstract class AbstractRestClient {

    private final ConnectionManager connectionManager;

    protected AbstractRestClient(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected <REQ extends RestRequest, RESP extends RestResponse<T>, T>
            void getRequest(String url, REQ request, StreamObserver<T> observer, Class<RESP> responseClass) {
        String requestUrl = buildUrl(url, request);
        Serializer serializer = connectionManager.getSerializer();
        connectionManager.getHttpClient().get(requestUrl, new RestCallback<>(observer, responseClass, serializer));
    }

    protected <REQ extends RestRequest, RESP extends RestResponse<T>, T>
            void postRequest(String url, REQ request, StreamObserver<T> observer, Class<RESP> responseClass) {
        try {
            Serializer serializer = connectionManager.getSerializer();
            String requestBody = serializer.writeObjectAsString(request);
            connectionManager.getHttpClient().post(url, requestBody, new RestCallback<>(observer, responseClass, serializer));
        } catch (IOException e) {
            observer.onError(new MatchbookSDKHttpException(e.getMessage(), e));
        }
    }

    protected <REQ extends RestRequest, RESP extends RestResponse<T>, T>
            void putRequest(String url, REQ request, StreamObserver<T> observer, Class<RESP> responseClass) {
        try {
            Serializer serializer = connectionManager.getSerializer();
            String requestBody = serializer.writeObjectAsString(request);
            connectionManager.getHttpClient().put(url, requestBody, new RestCallback<>(observer, responseClass, serializer));
        } catch (IOException e) {
            observer.onError(new MatchbookSDKHttpException(e.getMessage(), e));
        }
    }

    protected <REQ extends RestRequest, RESP extends RestResponse<T>, T>
            void deleteRequest(String url, REQ request, StreamObserver<T> observer, Class<RESP> responseClass) {
        String requestUrl = buildUrl(url, request);
        Serializer serializer = connectionManager.getSerializer();
        connectionManager.getHttpClient().delete(requestUrl, new RestCallback<>(observer, responseClass, serializer));
    }

    protected String buildSportsUrl(String path) {
        return connectionManager.getClientConfig().getSportsUrl() + "/" + path;
    }

    protected String buildLoginUrl() {
        return connectionManager.getClientConfig().getLoginUrl();
    }

    private <REQ extends RestRequest> String buildUrl(String baseUrl, REQ request) {
        return baseUrl + "?" + request.parameters().entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }

    private class RestCallback<T, RESP extends RestResponse<T>> implements HttpCallback {

        private final StreamObserver<T> observer;
        private final Class<RESP> responseClass;
        private final Serializer serializer;

        private RestCallback(StreamObserver<T> observer, Class<RESP> responseClass, Serializer serializer) {
            this.observer = observer;
            this.responseClass = responseClass;
            this.serializer = serializer;
        }

        @Override
        public void onResponse(InputStream inputStream) {
            try {
                RestResponse<T> restResponse = serializer.readObject(inputStream, responseClass);
                restResponse.getContent().forEach(observer::onNext);
                observer.onCompleted();
            } catch (IOException e) {
                MatchbookSDKHttpException exception = new MatchbookSDKHttpException(e.getMessage(), e);
                observer.onError(exception);
            }
        }

        @Override
        public void onFailure(MatchbookSDKHttpException exception) {
            observer.onError(exception);
        }

    }

}
