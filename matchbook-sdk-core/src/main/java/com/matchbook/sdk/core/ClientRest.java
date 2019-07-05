package com.matchbook.sdk.core;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.matchbook.sdk.core.configs.ClientConfig;
import com.matchbook.sdk.core.configs.ConnectionManager;
import com.matchbook.sdk.core.disruptor.CoordinatorMessage;
import com.matchbook.sdk.core.disruptor.CoordinatorPublisher;
import com.matchbook.sdk.core.disruptor.DisruptorCoordinator;
import com.matchbook.sdk.core.workers.PoolerWorker;
import com.matchbook.sdk.core.workers.RestPoolerWorker;

public class ClientRest implements Client {

    private final ConnectionManager connectionManager;


    private final Disruptor<CoordinatorMessage> disruptor;
    private final PoolerWorker poolerWorker;
    private final DisruptorCoordinator coordinator;

    public ClientRest(ClientConfig clientConfig) {
        this.connectionManager = new ConnectionManager(clientConfig);

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

    @Override
    public synchronized void close() {
        poolerWorker.halt();
        coordinator.halt();
    }

}
