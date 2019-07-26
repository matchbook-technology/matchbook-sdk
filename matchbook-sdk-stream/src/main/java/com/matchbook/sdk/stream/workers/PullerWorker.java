package com.matchbook.sdk.stream.workers;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.stream.model.dataobjects.user.Balance;

public interface PullerWorker extends AutoCloseable {

    StreamObserver<Void> observeBalance(StreamObserver<Balance> observer);

    @Override
    void close();
}
