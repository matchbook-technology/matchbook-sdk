package com.matchbook.sdk.stream;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.stream.model.dataobjects.beeting.Offer;
import com.matchbook.sdk.stream.model.dataobjects.beeting.Position;
import com.matchbook.sdk.stream.model.dataobjects.events.Event;
import com.matchbook.sdk.stream.model.dataobjects.user.Balance;

public interface StreamClient extends Client {

    StreamObserver<Void> observeBalance(StreamObserver<Balance> observer);

    StreamObserver<EventsFilter> observeEvent(StreamObserver<Event> observer);

    StreamObserver<Void> observePositions(StreamObserver<Position> observer);

    StreamObserver<Void> observeOffers(StreamObserver<Offer> observer);
}
