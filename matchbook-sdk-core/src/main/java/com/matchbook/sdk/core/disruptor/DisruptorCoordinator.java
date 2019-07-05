package com.matchbook.sdk.core.disruptor;

import com.lmax.disruptor.dsl.Disruptor;
import com.matchbook.sdk.core.disruptor.handlers.AggregatorHandler;
import com.matchbook.sdk.core.disruptor.handlers.PublisherHandler;
import com.matchbook.sdk.core.disruptor.handlers.TransformerHandler;

public class DisruptorCoordinator {

    private Disruptor<CoordinatorMessage> disruptor;

    public DisruptorCoordinator(Disruptor<CoordinatorMessage> disruptor) {
        this.disruptor = disruptor;
    }


    public void init() {
        disruptor.handleEventsWith(new TransformerHandler<>())
                .then(new AggregatorHandler<>())
                .then(new PublisherHandler<>());

        disruptor.start();

    }

    public void halt() {
        disruptor.shutdown();
    }
}
