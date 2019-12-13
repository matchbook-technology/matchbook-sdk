package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatPostRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatDeleteRequest;

public interface HeartbeatClient extends Client {

    void getHeartbeat(HeartbeatGetRequest heartbeatGetRequest, StreamObserver<Heartbeat> heartbeatObserver);

    void sendHeartbeat(HeartbeatPostRequest heartbeatPostRequest, StreamObserver<Heartbeat> heartbeatObserver);

    void unsubscribeHeartbeat(HeartbeatDeleteRequest heartbeatDeleteRequest,
            StreamObserver<Heartbeat> heartbeatObserver);

}
