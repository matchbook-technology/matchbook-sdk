package com.matchbook.sdk.core;

import com.matchbook.sdk.core.model.dataobjects.auth.User;
import com.matchbook.sdk.core.model.dataobjects.events.Event;
import com.matchbook.sdk.core.model.dataobjects.events.Sport;

public interface Client extends AutoCloseable {

    void getUserInfo(StreamObserver<User> observer);

    void getSports(StreamObserver<Sport> observer);

    void getEventStream(EventsFilter eventsFilter, StreamObserver<Event> observer);
}
