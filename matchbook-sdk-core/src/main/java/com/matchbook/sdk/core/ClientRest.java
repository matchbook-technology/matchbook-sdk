package com.matchbook.sdk.core;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import com.matchbook.sdk.core.configs.ClientConnectionManager;
import com.matchbook.sdk.core.services.Auth;
import com.matchbook.sdk.core.services.AuthRest;

public class ClientRest implements Client {

    private final AtomicReference<Auth> authClient;

    private ClientConfig clientConfig;
    private ClientConnectionManager clientConnectionManager;

    public ClientRest(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        this.clientConnectionManager = new ClientConnectionManager(clientConfig);

        this.authClient = new AtomicReference<>();
    }

    public Auth getAuthClient() {
        return newClient(authClient, AuthRest::new);
    }


    @Override
    public synchronized void close() {

        //TODO: close clients
    }


    private <T extends AutoCloseable> T newClient(AtomicReference<T> reference,
            Function<ClientConnectionManager, T> factory) {

        T client = reference.get();

        if (client == null) {
            synchronized (reference) {
                client = reference.get();
                if (client == null) {
                    client = factory.apply(clientConnectionManager);
                    reference.lazySet(client);
                }
            }
        }
        return client;
    }

}
