package com.matchbook.sdk.core.clients.rest;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.clients.rest.dtos.user.Logout;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHTTPException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

public class UserRestClientImpl extends AbstractRestClient implements UserRestClient {

    private final ClientConnectionManager clientConnectionManager;
    private final ObjectWriter loginRequestWriter;
    private final ObjectReader loginResponseReader;

    public UserRestClientImpl(ClientConnectionManager clientConnectionManager) {
        super(clientConnectionManager.getMapper());

        this.clientConnectionManager = clientConnectionManager;
        this.loginRequestWriter = clientConnectionManager.getMapper().writerFor(LoginRequest.class);
        this.loginResponseReader = clientConnectionManager.getMapper().readerFor(Login.class);
    }

    @Override
    public void login(LoginRequest loginRequest, StreamObserver<Login> observer) {
        try {
            String loginURL = clientConnectionManager.getClientConfig().getLoginUrl();
            String requestBody = loginRequestWriter.writeValueAsString(loginRequest);
            Request request = buildRequest(loginURL, requestBody);

            clientConnectionManager.getHttpClient()
                    .newCall(request)
                    .enqueue(new Callback() {
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
