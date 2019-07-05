package com.matchbook.sdk.core.workers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.core.clients.rest.UserRestClient;
import com.matchbook.sdk.core.clients.rest.UserRestClientImpl;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.configs.ConnectionManager;
import com.matchbook.sdk.core.disruptor.CoordinatorPublisher;
import com.matchbook.sdk.core.schedulers.AuthenticationScheduler;

public class RestPoolerWorker implements PoolerWorker {

    private final CoordinatorPublisher coordinatorPublisher;
    private final ConnectionManager connectionManager;

    private final ScheduledExecutorService authScheduler;
    private final ScheduledExecutorService eventsScheduler;

    public RestPoolerWorker(CoordinatorPublisher coordinatorPublisher, ConnectionManager connectionManager) {
        this.coordinatorPublisher = coordinatorPublisher;
        this.connectionManager = connectionManager;

        authScheduler = Executors.newScheduledThreadPool(1);
        eventsScheduler = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void init() {
        initAuthenticationScheduler();
        initEventsScheduler();
    }

    @Override
    public void halt() {
        authScheduler.shutdown();
        eventsScheduler.shutdown();
    }

    private void initAuthenticationScheduler() {
        UserRestClient userRestClient = new UserRestClientImpl(connectionManager);
        LoginRequest loginRequest =
                new LoginRequest.Builder(connectionManager.getClientConfig().getUsername(),
                        connectionManager.getClientConfig().getPassword()).build();

        authScheduler.scheduleAtFixedRate(new AuthenticationScheduler(coordinatorPublisher,
                        userRestClient, loginRequest),
                0, 4, TimeUnit.HOURS);
    }

    private void initEventsScheduler() {
        eventsScheduler.scheduleAtFixedRate(() -> {
            //TODO: lookup to events interested
        }, 0, 10, TimeUnit.SECONDS);
    }
}
