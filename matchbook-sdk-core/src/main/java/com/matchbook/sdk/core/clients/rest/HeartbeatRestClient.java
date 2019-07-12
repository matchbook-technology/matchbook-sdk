package com.matchbook.sdk.core.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.HeartbeatSendRequest;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.HeartbeatUnsubscribeRequest;

public interface HeartbeatRestClient {

    void getHeartbeat(HeartbeatGetRequest heartbeatGetRequest, StreamObserver<Heartbeat> heartbeatObserver);

    void sendHeartbeat(HeartbeatSendRequest heartbeatSendRequest, StreamObserver<Heartbeat> heartbeatObserver);

    void unsubscribeHeartbeat(HeartbeatUnsubscribeRequest heartbeatUnsubscribeRequest,
        StreamObserver<Heartbeat> heartbeatObserver);

}
