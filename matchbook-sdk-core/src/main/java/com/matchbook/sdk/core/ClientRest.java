package com.matchbook.sdk.core;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import com.matchbook.sdk.core.configs.ClientConfig;
import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.matchbook.sdk.core.services.AuthService;
import com.matchbook.sdk.core.services.AuthRestService;
import com.matchbook.sdk.core.services.ClientService;

public class ClientRest implements Client {

    private final ClientConnectionManager clientConnectionManager;
    private final AtomicReference<AuthService> authClient;

    public ClientRest(ClientConfig clientConfig) {
        this.clientConnectionManager = new ClientConnectionManager(clientConfig);
        this.authClient = new AtomicReference<>();
    }

    public AuthService getAuthService() {
        return getService(authClient, AuthRestService::new);
    }

    @Override
    public synchronized void close() {
        //TODO: close clients
    }

    private <T extends ClientService> T getService(AtomicReference<T> serviceReference,
            Function<ClientConnectionManager, T> serviceFactory) {
        T client = serviceReference.get();
        if (Objects.isNull(client)) {
            synchronized (serviceReference) {
                client = serviceReference.get();
                if (Objects.isNull(client)) {
                    client = serviceFactory.apply(clientConnectionManager);
                    serviceReference.lazySet(client);
                }
            }
        }
        return client;
    }

}
