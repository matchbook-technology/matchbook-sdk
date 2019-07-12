package com.matchbook.sdk.core;

import com.matchbook.sdk.core.model.dataobjects.beeting.Offer;
import com.matchbook.sdk.core.model.dataobjects.beeting.Position;
import com.matchbook.sdk.core.model.dataobjects.events.Event;
import com.matchbook.sdk.core.model.dataobjects.user.Balance;

public interface StreamClient extends Client {

    StreamObserver<Void> observeBalance(StreamObserver<Balance> observer);

    StreamObserver<EventsFilter> observeEvent(StreamObserver<Event> observer);

    StreamObserver<Void> observePositions(StreamObserver<Position> observer);

    StreamObserver<Void> observeOffers(StreamObserver<Offer> observer);
}
