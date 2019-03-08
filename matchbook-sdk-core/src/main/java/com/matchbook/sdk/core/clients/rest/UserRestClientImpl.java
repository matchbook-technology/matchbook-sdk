package com.matchbook.sdk.core.clients.rest;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.Logout;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHTTPException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

public class UserRestClientImpl extends AbstractRestClient implements UserRestClient {

    private static final String JSON_TYPE = "application/json";
    private final MediaType mediaType;
    private final ClientConnectionManager clientConnectionManager;
    private final String loginURL;
    private final ObjectReader loginResponseReader;
    private final ObjectWriter loginResponseWriter;

    public UserRestClientImpl(ClientConnectionManager clientConnectionManager) {
        super(clientConnectionManager.getMapper());

        this.clientConnectionManager = clientConnectionManager;
        this.loginResponseReader = clientConnectionManager.getMapper().readerFor(Login.class);
        this.loginResponseWriter = clientConnectionManager.getMapper().writerFor(LoginRequest.class);
        this.loginURL = clientConnectionManager.getClientConfig().getLoginUrl();
        this.mediaType = MediaType.parse(JSON_TYPE);
    }

    @Override
    public void login(LoginRequest loginRequest, StreamObserver<Login> observer) {
        try {
            RequestBody requestBody
                    = RequestBody.create(mediaType, loginResponseWriter.writeValueAsString(loginRequest));

            Request request = new Request.Builder()
                    .url(loginURL)
                    .addHeader("Accept", JSON_TYPE)
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
                            errorHandler(response, observer);
                            return;
                        }
                        observer.onNext(loginResponseReader.readValue(responseBody.byteStream()));
                        observer.onCompleted();
                    }
                }
            });
        } catch (Exception e) {
            observer.onError(new MatchbookSDKHTTPException((e.getCause())));
        }
    }

    @Override
    public void logout(String sessionToken, StreamObserver<Logout> response) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
