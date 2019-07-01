package com.matchbook.sdk.core;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import com.matchbook.sdk.core.configs.ClientConfig;
import com.matchbook.sdk.core.configs.ConnectionManager;
import com.matchbook.sdk.core.services.AuthRestService;
import com.matchbook.sdk.core.services.AuthService;
import com.matchbook.sdk.core.services.ClientService;

public class ClientRest implements Client {

    private final ConnectionManager connectionManager;
    private final AtomicReference<AuthService> authServiceReference;

    public ClientRest(ClientConfig clientConfig) {
        this.connectionManager = new ConnectionManager(clientConfig);
        this.authServiceReference = new AtomicReference<>();
    }

    public AuthService getAuthService() {
        return getService(authServiceReference, AuthRestService::new);
    }

    @Override
    public synchronized void close() {
        //TODO: close clients
    }

    private <T extends ClientService> T getService(AtomicReference<T> serviceReference,
            Function<ConnectionManager, T> serviceFactory) {
        T client = serviceReference.get();
        if (Objects.isNull(client)) {
            synchronized (serviceReference) {
                client = serviceReference.get();
                if (Objects.isNull(client)) {
                    client = serviceFactory.apply(connectionManager);
                    serviceReference.lazySet(client);
                }
            }
        }
        return client;
    }
}
