package com.matchbook.sdk.clients.rest;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.matchbook.sdk.StreamObserver;
import com.matchbook.sdk.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.clients.rest.dtos.user.LoginResponse;
import com.matchbook.sdk.clients.rest.dtos.user.LogoutResponse;
import com.matchbook.sdk.configs.ClientConnectionManager;
import com.matchbook.sdk.exceptions.MatchbookSDKHTTPException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

public class UserRestClientImpl implements UserRestClient {

    private final MediaType mediaType;
    private final ClientConnectionManager clientConnectionManager;
    private final String loginURL;
    private final ObjectReader loginResponseReader;
    private final ObjectWriter loginResponseWriter;

    public UserRestClientImpl(ClientConnectionManager clientConnectionManager) {
        this.clientConnectionManager = clientConnectionManager;
        this.loginResponseReader = clientConnectionManager.getMapper().readerFor(LoginResponse.class);
        this.loginResponseWriter = clientConnectionManager.getMapper().writerFor(LoginRequest.class);
        this.loginURL = clientConnectionManager.getClientConfig().getLoginUrl();
        this.mediaType = MediaType.parse("application/json");
    }

    @Override
    public void login(LoginRequest loginRequest, StreamObserver<LoginResponse> observer) {
        try {
            RequestBody requestBody
                    = RequestBody.create(mediaType, loginResponseWriter.writeValueAsString(loginRequest));

            Request request = new Request.Builder()
                    .url(loginURL)
                    .addHeader("Accept", "application/json")
                    .post(requestBody)
                    .build();

            clientConnectionManager.getHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    observer.onError(new MatchbookSDKHTTPException(e.getMessage(), e));
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try (ResponseBody responseBody = response.body()) {
                        if (!response.isSuccessful()) {
                            observer.onError(new MatchbookSDKHTTPException(("Unexpected HTTP code " + response)));
                        } else {
                            observer.onNext(loginResponseReader.readValue(responseBody.byteStream()));
                            observer.onCompleted();
                        }
                    }
                }
            });
        } catch (Exception e) {
            observer.onError(new MatchbookSDKHTTPException((e.getCause())));
        }
    }

    @Override
    public void logout(String sessionToken, StreamObserver<LogoutResponse> response) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
