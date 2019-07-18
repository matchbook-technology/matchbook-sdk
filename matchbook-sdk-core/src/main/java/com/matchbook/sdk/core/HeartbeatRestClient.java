package com.matchbook.sdk.core;

import com.matchbook.sdk.common.StreamObserver;
import com.matchbook.sdk.core.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.core.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.core.dtos.heartbeat.HeartbeatSendRequest;
import com.matchbook.sdk.core.dtos.heartbeat.HeartbeatUnsubscribeRequest;

public interface HeartbeatRestClient {

    void getHeartbeat(HeartbeatGetRequest heartbeatGetRequest, StreamObserver<Heartbeat> heartbeatObserver);

    void sendHeartbeat(HeartbeatSendRequest heartbeatSendRequest, StreamObserver<Heartbeat> heartbeatObserver);

    void unsubscribeHeartbeat(HeartbeatUnsubscribeRequest heartbeatUnsubscribeRequest,
        StreamObserver<Heartbeat> heartbeatObserver);

}
