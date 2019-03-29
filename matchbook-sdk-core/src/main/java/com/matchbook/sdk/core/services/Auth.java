package com.matchbook.sdk.core.services;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.model.dataobjects.LoginEnvelope;
import com.matchbook.sdk.core.model.dataobjects.auth.User;

public interface Auth extends ClientService {

    void login(User user, StreamObserver<LoginEnvelope> streamObserver);
}
