package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.RestRequest;
import com.matchbook.sdk.rest.dtos.RestResponse;
import com.matchbook.sdk.rest.readers.Reader;

import java.io.IOException;
import java.util.stream.Collectors;

abstract class AbstractClientRest implements Client {

    protected final ConnectionManager connectionManager;

    protected AbstractClientRest(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected <REQ extends RestRequest, RESP extends RestResponse, T> void getRequest(
            String url,
            REQ request,
            StreamObserver<T> observer,
            Reader<T, RESP> reader) {
        String requestUrl = buildUrl(url, request);
        Serializer serializer = connectionManager.getSerializer();
        connectionManager.getHttpClient().get(requestUrl, new RestResponseCallback<>(observer, serializer, reader));
    }

    protected <REQ extends RestRequest, RESP extends RestResponse, T> void postRequest(
            String url,
            REQ request,
            StreamObserver<T> observer,
            Reader<T, RESP> reader) {
        try {
            Serializer serializer = connectionManager.getSerializer();
            String requestBody = serializer.writeObjectAsString(request);
            connectionManager.getHttpClient().post(url, requestBody, new RestResponseCallback<>(observer, serializer, reader));
        } catch (IOException e) {
            observer.onError(new MatchbookSDKHttpException(e.getMessage(), e));
        }
    }

    protected <REQ extends RestRequest, RESP extends RestResponse, T> void putRequest(
            String url,
            REQ request,
            StreamObserver<T> observer,
            Reader<T, RESP> reader) {
        try {
            Serializer serializer = connectionManager.getSerializer();
            String requestBody = serializer.writeObjectAsString(request);
            connectionManager.getHttpClient().put(url, requestBody, new RestResponseCallback<>(observer, serializer, reader));
        } catch (IOException e) {
            observer.onError(new MatchbookSDKHttpException(e.getMessage(), e));
        }
    }

    protected <REQ extends RestRequest, RESP extends RestResponse, T> void deleteRequest(
            String url,
            REQ request,
            StreamObserver<T> observer,
            Reader<T, RESP> reader) {
        String requestUrl = buildUrl(url, request);
        Serializer serializer = connectionManager.getSerializer();
        connectionManager.getHttpClient().delete(requestUrl, new RestResponseCallback<>(observer, serializer, reader));
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

}
