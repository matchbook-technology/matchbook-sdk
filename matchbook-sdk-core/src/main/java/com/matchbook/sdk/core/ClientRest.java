package com.matchbook.sdk.core;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.matchbook.sdk.core.clients.rest.EventsRestClient;
import com.matchbook.sdk.core.clients.rest.EventsRestClientImpl;
import com.matchbook.sdk.core.clients.rest.dtos.events.SportsRequest;
import com.matchbook.sdk.core.configs.ClientConfig;
import com.matchbook.sdk.core.configs.ConnectionManager;
import com.matchbook.sdk.core.disruptor.CoordinatorMessage;
import com.matchbook.sdk.core.disruptor.CoordinatorPublisher;
import com.matchbook.sdk.core.disruptor.DisruptorCoordinator;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.model.dataobjects.auth.User;
import com.matchbook.sdk.core.model.dataobjects.events.Event;
import com.matchbook.sdk.core.model.dataobjects.events.Sport;
import com.matchbook.sdk.core.model.mappers.events.SportMapper;
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
    public void getUserInfo(StreamObserver<User> observer) {
        // TODO: get current user information from memory
    }

    @Override
    public void getSports(StreamObserver<Sport> observer) {
        EventsRestClient eventsRestClient = new EventsRestClientImpl(connectionManager);

        eventsRestClient.getSports(new SportsRequest.Builder().build(),
                new StreamObserver<com.matchbook.sdk.core.clients.rest.dtos.events.Sport>() {
                    @Override
                    public void onNext(com.matchbook.sdk.core.clients.rest.dtos.events.Sport sport) {
                        observer.onNext(SportMapper.mapDtoToModel(sport));
                    }

                    @Override
                    public void onCompleted() {
                        observer.onCompleted();
                    }

                    @Override
                    public <E extends MatchbookSDKException> void onError(E exception) {
                        observer.onError(exception);
                    }
                });
    }

    @Override
    public void getEventStream(EventsFilter eventsFilter, StreamObserver<Event> observer) {
        // TODO: get snapshot data and subscribe on updates
    }

    @Override
    public synchronized void close() {
        poolerWorker.halt();
        coordinator.halt();
    }

}
