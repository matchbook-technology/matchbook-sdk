package com.matchbook.sdk.core.workers;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.model.dataobjects.user.Balance;

public interface PullerWorker extends AutoCloseable {

    StreamObserver<Void> observeBalance(StreamObserver<Balance> observer);

    @Override
    void close();
}
