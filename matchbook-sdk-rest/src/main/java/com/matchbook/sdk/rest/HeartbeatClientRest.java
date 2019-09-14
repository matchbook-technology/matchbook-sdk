package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatSendRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatUnsubscribeRequest;
import com.matchbook.sdk.rest.readers.heartbeat.HeartbeatReader;

public class HeartbeatClientRest extends AbstractRestClient implements HeartbeatClient {

    public HeartbeatClientRest(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void getHeartbeat(HeartbeatGetRequest heartbeatGetRequest, StreamObserver<Heartbeat> heartbeatObserver) {
        String url = buildSportsUrl(heartbeatGetRequest.resourcePath());
        getRequest(url, heartbeatGetRequest, heartbeatObserver, new HeartbeatReader());
    }

    @Override
    public void sendHeartbeat(HeartbeatSendRequest heartbeatSendRequest, StreamObserver<Heartbeat> heartbeatObserver) {
        String url = buildSportsUrl(heartbeatSendRequest.resourcePath());
        postRequest(url, heartbeatSendRequest, heartbeatObserver, new HeartbeatReader());
    }

    @Override
    public void unsubscribeHeartbeat(HeartbeatUnsubscribeRequest heartbeatUnsubscribeRequest,
        StreamObserver<Heartbeat> heartbeatObserver) {
        String url = buildSportsUrl(heartbeatUnsubscribeRequest.resourcePath());
        deleteRequest(url, heartbeatUnsubscribeRequest, heartbeatObserver, new HeartbeatReader());
    }
}
