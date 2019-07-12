package com.matchbook.sdk.core;

import java.util.concurrent.atomic.AtomicBoolean;

import com.matchbook.sdk.core.configs.ConnectionManager;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.model.dataobjects.beeting.Offer;
import com.matchbook.sdk.core.model.dataobjects.beeting.Position;
import com.matchbook.sdk.core.model.dataobjects.events.Event;
import com.matchbook.sdk.core.model.dataobjects.user.Balance;
import com.matchbook.sdk.core.workers.PullerWorker;
import com.matchbook.sdk.core.workers.PullerWorkerRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamClientRest implements StreamClient {

    private static final Logger LOG = LoggerFactory.getLogger(StreamClientRest.class);

    private final ConnectionManager connectionManager;
    private final PullerWorker pullerWorker;

    private final AtomicBoolean isShutdown;

    public StreamClientRest(ClientConfig clientConfig) {
        this.connectionManager = new ConnectionManager(clientConfig);
        this.isShutdown = new AtomicBoolean();

        LOG.info("Matchbook client starting...");
        pullerWorker = new PullerWorkerRest(connectionManager);
        LOG.info("Matchbook client start completed.");
    }

    @Override
    public StreamObserver<Void> observeBalance(StreamObserver<Balance> observer) {

        return pullerWorker.observeBalance(new StreamObserver<Balance>() {
            @Override
            public void onNext(Balance balance) {
                observer.onNext(balance);
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
    public StreamObserver<EventsFilter> observeEvent(StreamObserver<Event> observer) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public StreamObserver<Void> observePositions(StreamObserver<Position> observer) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public StreamObserver<Void> observeOffers(StreamObserver<Offer> observer) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void close() {
        if (isShutdown.getAndSet(true)) {
            return;
        }

        LOG.info("Matchbook client shutdown initiated...");
        pullerWorker.close();
        LOG.info("Matchbook client shutdown completed.");
    }

}
