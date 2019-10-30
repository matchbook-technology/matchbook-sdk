package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.ConnectionManager;
import com.matchbook.sdk.rest.configs.HttpCallback;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.FailableRestResponse;
import com.matchbook.sdk.rest.dtos.PartiallyFailableResponse;
import com.matchbook.sdk.rest.dtos.RestRequest;
import com.matchbook.sdk.rest.dtos.RestResponse;
import com.matchbook.sdk.rest.readers.Reader;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

abstract class BaseClientRest implements Client {

    protected final ConnectionManager connectionManager;

    protected BaseClientRest(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected String buildSportsUrl(String path) {
        return connectionManager.getClientConfig().getSportsUrl() + "/" + path;
    }

    protected String buildLoginUrl() {
        return connectionManager.getClientConfig().getLoginUrl();
    }

    protected <REQ extends RestRequest, RESP extends RestResponse, T> void getRequest(
            String url, REQ request, StreamObserver<T> observer, Reader<T, RESP> reader) {
        String requestUrl = buildUrl(url, request);
        Serializer serializer = connectionManager.getSerializer();
        connectionManager.getHttpClient().get(requestUrl, new RestResponseCallback<>(observer, serializer, reader));
    }

    protected <REQ extends RestRequest, RESP extends PartiallyFailableResponse<T>, T extends FailableRestResponse>
            void partiallyFailablePostRequest(String url, REQ request, StreamObserver<T> observer, Reader<T, RESP> reader) {
        HttpCallback httpCallback = new PartiallyFailableResponseCallback<>(observer, connectionManager.getSerializer(), reader);
        postRequest(url, request, observer, httpCallback);
    }

    protected <REQ extends RestRequest, RESP extends RestResponse, T> void postRequest(
            String url, REQ request, StreamObserver<T> observer, Reader<T, RESP> reader) {
        HttpCallback httpCallback = new RestResponseCallback<>(observer, connectionManager.getSerializer(), reader);
        postRequest(url, request, observer, httpCallback);
    }

    private <REQ extends RestRequest, T> void postRequest(String url, REQ request,
            StreamObserver<T> observer, HttpCallback httpCallback) {
        try {
            Serializer serializer = connectionManager.getSerializer();
            String requestBody = serializer.writeObjectAsString(request);
            connectionManager.getHttpClient().post(url, requestBody, httpCallback);
        } catch (IOException ioe) {
            MatchbookSDKParsingException matchbookException = new MatchbookSDKParsingException(ioe.getMessage(), ioe);
            observer.onError(matchbookException);
        }
    }

    protected <REQ extends RestRequest, RESP extends PartiallyFailableResponse<T>, T extends FailableRestResponse>
            void partiallyFailablePutRequest(String url, REQ request, StreamObserver<T> observer, Reader<T, RESP> reader) {
        HttpCallback httpCallback = new PartiallyFailableResponseCallback<>(observer, connectionManager.getSerializer(), reader);
        putRequest(url, request, observer, httpCallback);
    }

    protected <REQ extends RestRequest, RESP extends RestResponse, T> void putRequest(
            String url, REQ request, StreamObserver<T> observer, Reader<T, RESP> reader) {
        HttpCallback httpCallback = new RestResponseCallback<>(observer, connectionManager.getSerializer(), reader);
        putRequest(url, request, observer, httpCallback);
    }

    private <REQ extends RestRequest, T> void putRequest(String url, REQ request,
            StreamObserver<T> observer, HttpCallback httpCallback) {
        try {
            Serializer serializer = connectionManager.getSerializer();
            String requestBody = serializer.writeObjectAsString(request);
            connectionManager.getHttpClient().put(url, requestBody, httpCallback);
        } catch (IOException ioe) {
            MatchbookSDKParsingException matchbookException = new MatchbookSDKParsingException(ioe.getMessage(), ioe);
            observer.onError(matchbookException);
        }
    }

    protected <REQ extends RestRequest, RESP extends PartiallyFailableResponse<T>, T extends FailableRestResponse>
            void partiallyFailableDeleteRequest(String url, REQ request, StreamObserver<T> observer, Reader<T, RESP> reader) {
        HttpCallback httpCallback = new PartiallyFailableResponseCallback<>(observer, connectionManager.getSerializer(), reader);
        deleteRequest(url, request, httpCallback);
    }

    protected <REQ extends RestRequest, RESP extends RestResponse, T> void deleteRequest(
            String url, REQ request, StreamObserver<T> observer, Reader<T, RESP> reader) {
        HttpCallback httpCallback = new RestResponseCallback<>(observer, connectionManager.getSerializer(), reader);
        deleteRequest(url, request, httpCallback);
    }

    private <REQ extends RestRequest, T> void deleteRequest(String url, REQ request, HttpCallback httpCallback) {
        String requestUrl = buildUrl(url, request);
        connectionManager.getHttpClient().delete(requestUrl, httpCallback);
    }

    private <REQ extends RestRequest> String buildUrl(String baseUrl, REQ request) {
        Map<String, String> queryParameters = request.parameters();
        return baseUrl + (queryParameters.isEmpty() ? "" :
                "?" + queryParameters.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue())
                        .collect(Collectors.joining("&"))
        );
    }

}
