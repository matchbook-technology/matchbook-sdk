package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.rest.configs.HttpCallback;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.Reader;
import com.matchbook.sdk.rest.dtos.RestRequest;
import com.matchbook.sdk.rest.dtos.RestResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;

abstract class AbstractRestClient {

    protected final ConnectionManager connectionManager;

    protected AbstractRestClient(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected <REQ extends RestRequest, RESP extends RestResponse<T>, T> void getRequest(
            String url,
            REQ request,
            StreamObserver<T> observer,
            Reader<T, RESP> reader) {
        String requestUrl = buildUrl(url, request);
        Serializer serializer = connectionManager.getSerializer();
        connectionManager.getHttpClient().get(requestUrl, new RestCallback<>(observer, serializer, reader));
    }

    protected <REQ extends RestRequest, RESP extends RestResponse<T>, T> void postRequest(
            String url,
            REQ request,
            StreamObserver<T> observer,
            Reader<T, RESP> reader) {
        try {
            Serializer serializer = connectionManager.getSerializer();
            String requestBody = serializer.writeObjectAsString(request);
            connectionManager.getHttpClient().post(url, requestBody, new RestCallback<>(observer, serializer, reader));
        } catch (IOException e) {
            observer.onError(new MatchbookSDKHttpException(e.getMessage(), e));
        }
    }

    protected <REQ extends RestRequest, RESP extends RestResponse<T>, T> void putRequest(
            String url,
            REQ request,
            StreamObserver<T> observer,
            Reader<T, RESP> reader) {
        try {
            Serializer serializer = connectionManager.getSerializer();
            String requestBody = serializer.writeObjectAsString(request);
            connectionManager.getHttpClient().put(url, requestBody, new RestCallback<>(observer, serializer, reader));
        } catch (IOException e) {
            observer.onError(new MatchbookSDKHttpException(e.getMessage(), e));
        }
    }

    protected <REQ extends RestRequest, RESP extends RestResponse<T>, T> void deleteRequest(
            String url,
            REQ request,
            StreamObserver<T> observer,
            Reader<T, RESP> reader) {
        String requestUrl = buildUrl(url, request);
        Serializer serializer = connectionManager.getSerializer();
        connectionManager.getHttpClient().delete(requestUrl, new RestCallback<>(observer, serializer, reader));
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

    private static class RestCallback<T, RESP extends RestResponse<T>> implements HttpCallback {

        private final StreamObserver<T> observer;
        private final Serializer serializer;
        private final Reader<T, RESP> reader;

        private RestCallback(StreamObserver<T> observer, Serializer serializer, Reader<T, RESP> reader) {
            this.observer = observer;
            this.serializer = serializer;
            this.reader = reader;
        }

        @Override
        public void onResponse(InputStream inputStream) {
            try (Parser parser = serializer.newParser(inputStream)) {
                reader.startReading(parser);
                while (reader.hasMoreItems()) {
                    T item = reader.readNextItem();
                    observer.onNext(item);
                }
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
