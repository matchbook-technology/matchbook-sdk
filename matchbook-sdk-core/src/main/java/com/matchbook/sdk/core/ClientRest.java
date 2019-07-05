package com.matchbook.sdk.core;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.matchbook.sdk.core.configs.ClientConfig;
import com.matchbook.sdk.core.configs.ConnectionManager;
import com.matchbook.sdk.core.disruptor.CoordinatorMessage;
import com.matchbook.sdk.core.disruptor.CoordinatorPublisher;
import com.matchbook.sdk.core.disruptor.DisruptorCoordinator;
import com.matchbook.sdk.core.services.AuthRestService;
import com.matchbook.sdk.core.services.AuthService;
import com.matchbook.sdk.core.services.ClientService;
import com.matchbook.sdk.core.workers.PoolerWorker;
import com.matchbook.sdk.core.workers.RestPoolerWorker;

public class ClientRest implements Client {

    private final ConnectionManager connectionManager;
    private final AtomicReference<AuthService> authServiceReference;


    private final Disruptor<CoordinatorMessage> disruptor;
    private final PoolerWorker poolerWorker;
    private final DisruptorCoordinator coordinator;

    public ClientRest(ClientConfig clientConfig) {
        this.connectionManager = new ConnectionManager(clientConfig);
        this.authServiceReference = new AtomicReference<>();

        this.disruptor = new Disruptor<>(CoordinatorMessage::new,
                1024,
                DaemonThreadFactory.INSTANCE,
                ProducerType.MULTI,
                new BlockingWaitStrategy());

        coordinator = new DisruptorCoordinator(disruptor);
        coordinator.init();

        CoordinatorPublisher coordinatorPublisher = new CoordinatorPublisher(disruptor);
        poolerWorker = new RestPoolerWorker(coordinatorPublisher, connectionManager);
        poolerWorker.init();
    }

    public AuthService getAuthService() {
        return getService(authServiceReference, AuthRestService::new);
    }

    @Override
    public synchronized void close() {
        poolerWorker.halt();
        coordinator.halt();
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
