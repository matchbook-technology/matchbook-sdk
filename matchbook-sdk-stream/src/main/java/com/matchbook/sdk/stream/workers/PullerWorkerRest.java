package com.matchbook.sdk.stream.workers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.ConnectionManager;
import com.matchbook.sdk.rest.UserClient;
import com.matchbook.sdk.rest.UserClientRest;
import com.matchbook.sdk.stream.disruptor.UserDisruptorPipeliner;
import com.matchbook.sdk.stream.disruptor.messages.UserMessage;
import com.matchbook.sdk.stream.disruptor.publisher.UserPublisher;
import com.matchbook.sdk.stream.model.dataobjects.user.Balance;
import com.matchbook.sdk.stream.schedulers.AuthenticationScheduler;
import com.matchbook.sdk.stream.schedulers.BalanceScheduler;

public class PullerWorkerRest implements PullerWorker {

    private final ConnectionManager connectionManager;

    private final ScheduledExecutorService authScheduler;
    private final ScheduledExecutorService balanceScheduler;
    private final UserDisruptorPipeliner userDisruptorPipeliner;

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
        userDisruptorPipeliner = new UserDisruptorPipeliner(accountDisruptor);

        keepAliveSession();
    }

    private void keepAliveSession() {
        UserClient userRestClient = new UserClientRest(connectionManager);

        authScheduler.scheduleAtFixedRate(new AuthenticationScheduler(userRestClient),
                0, 4, TimeUnit.HOURS);
    }

    @Override
    public StreamObserver<Void> observeBalance(StreamObserver<Balance> observer) {
        userDisruptorPipeliner.getUserPublisherHandler().registerStreamConsumer(observer);

        balanceScheduler.scheduleAtFixedRate(new BalanceScheduler(new UserClientRest(connectionManager),
                        new UserPublisher(accountDisruptor)),
                0,
                5,
                TimeUnit.SECONDS);

        return new StreamObserver<Void>() {
            @Override
            public void onNext(Void aVoid) {
                //do nothing
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
    public void close() {
        // shutdown disruptor
        accountDisruptor.shutdown();
        //shutdown schedulers
        authScheduler.shutdown();
        accountDisruptor.shutdown();
    }
}
