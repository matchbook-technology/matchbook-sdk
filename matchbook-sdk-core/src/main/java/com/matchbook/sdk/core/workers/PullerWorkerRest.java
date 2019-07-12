package com.matchbook.sdk.core.workers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.matchbook.sdk.core.EventsFilter;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.UserRestClient;
import com.matchbook.sdk.core.clients.rest.UserRestClientImpl;
import com.matchbook.sdk.core.clients.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.core.configs.ConnectionManager;
import com.matchbook.sdk.core.disruptor.UserDisruptorPipeliner;
import com.matchbook.sdk.core.disruptor.messages.UserMessage;
import com.matchbook.sdk.core.disruptor.publisher.UserPublisher;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.model.dataobjects.events.Event;
import com.matchbook.sdk.core.model.dataobjects.user.Balance;
import com.matchbook.sdk.core.schedulers.AuthenticationScheduler;
import com.matchbook.sdk.core.schedulers.BalanceScheduler;

public class PullerWorkerRest implements PullerWorker {

    private final ConnectionManager connectionManager;

    private final ScheduledExecutorService authScheduler;
    private final ScheduledExecutorService balanceScheduler;

    private Disruptor<UserMessage> accountDisruptor;


    public PullerWorkerRest(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;

        //TODO: property must be provided from configuration
        authScheduler = Executors.newScheduledThreadPool(1);
        balanceScheduler = Executors.newScheduledThreadPool(1);

        accountDisruptor = new Disruptor<>(UserMessage::new,
                1024,
                DaemonThreadFactory.INSTANCE,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        keepAliveSession();
    }

    private void keepAliveSession() {
        UserRestClient userRestClient = new UserRestClientImpl(connectionManager);
        LoginRequest loginRequest =
                new LoginRequest.Builder(connectionManager.getClientConfig().getUsername(),
                        connectionManager.getClientConfig().getPassword()).build();

        authScheduler.scheduleAtFixedRate(new AuthenticationScheduler(userRestClient, loginRequest),
                0, 4, TimeUnit.HOURS);
    }


    @Override
    public StreamObserver<Void> observeBalance(StreamObserver<Balance> observer) {
        UserRestClient userRestClient = new UserRestClientImpl(connectionManager);
        UserPublisher userPublisher = new UserPublisher(accountDisruptor);

        UserDisruptorPipeliner userDisruptorPipeliner = new UserDisruptorPipeliner(accountDisruptor);

        balanceScheduler.scheduleAtFixedRate(new BalanceScheduler(userRestClient, userPublisher),
                0,
                5,
                TimeUnit.SECONDS);

        return new StreamObserver<Void>() {
            @Override
            public void onNext(Void aVoid) {
                userDisruptorPipeliner.getUserPublisherHandler()
                        .registerConsumer(observer);
            }

            @Override
            public void onCompleted() {
                balanceScheduler.shutdown();
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {
                balanceScheduler.shutdown();
            }
        };
    }

    @Override
    public StreamObserver<EventsFilter> observeEvents(StreamObserver<Event> observer) {

        return new StreamObserver<EventsFilter>() {
            @Override
            public void onNext(EventsFilter eventsFilter) {

            }

            @Override
            public void onCompleted() {
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {

            }
        };

    }

    @Override
    public void close() {
        accountDisruptor.shutdown();

        authScheduler.shutdown();
        accountDisruptor.shutdown();
    }

}
