package com.matchbook.sdk.core;

import com.matchbook.sdk.core.services.AuthService;

public interface Client extends AutoCloseable {

    AuthService getAuthService();

}
