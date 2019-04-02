package com.matchbook.sdk.core.services;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.model.dataobjects.LoginEnvelope;
import com.matchbook.sdk.core.model.dataobjects.auth.Credentials;

public interface Auth extends ClientService {

    void login(Credentials credentials, StreamObserver<LoginEnvelope> streamObserver);
}
