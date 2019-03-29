package com.matchbook.sdk.core;

import com.matchbook.sdk.core.services.Auth;

public interface Client extends AutoCloseable {

    Auth getAuthClient();
}
