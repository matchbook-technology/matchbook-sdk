package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatSendRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatUnsubscribeRequest;

public interface HeartbeatClient extends Client {

    void getHeartbeat(HeartbeatGetRequest heartbeatGetRequest, StreamObserver<Heartbeat> heartbeatObserver);

    void sendHeartbeat(HeartbeatSendRequest heartbeatSendRequest, StreamObserver<Heartbeat> heartbeatObserver);

    void unsubscribeHeartbeat(HeartbeatUnsubscribeRequest heartbeatUnsubscribeRequest,
            StreamObserver<Heartbeat> heartbeatObserver);

}
