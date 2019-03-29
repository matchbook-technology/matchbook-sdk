package com.matchbook.sdk.core.services;

import com.matchbook.sdk.core.configs.ClientConnectionManager;

public class AuthRest implements Auth {

    private ClientConnectionManager clientConnectionManager;

    public AuthRest(ClientConnectionManager clientConnectionManager) {
        this.clientConnectionManager = clientConnectionManager;
    }


    @Override
    public void close() throws Exception {

    }
}
