package com.matchbook.sdk.core.clients.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.HeartbeatSendRequest;
import com.matchbook.sdk.core.clients.rest.dtos.heartbeat.HeartbeatUnsubscribeRequest;
import com.matchbook.sdk.core.configs.ConnectionManager;

public class HeartbeatRestClientImpl extends AbstractRestClient implements HeartbeatRestClient {

    public HeartbeatRestClientImpl(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void getHeartbeat(HeartbeatGetRequest heartbeatGetRequest, StreamObserver<Heartbeat> heartbeatObserver) {
        String url = buildSportsUrl(heartbeatGetRequest.resourcePath());
        getRequest(url, heartbeatGetRequest, heartbeatObserver, Heartbeat.class);
    }

    @Override
    public void sendHeartbeat(HeartbeatSendRequest heartbeatSendRequest, StreamObserver<Heartbeat> heartbeatObserver) {
        String url = buildSportsUrl(heartbeatSendRequest.resourcePath());
        postRequest(url, heartbeatSendRequest, heartbeatObserver, Heartbeat.class);
    }

    @Override
    public void unsubscribeHeartbeat(HeartbeatUnsubscribeRequest heartbeatUnsubscribeRequest,
        StreamObserver<Heartbeat> heartbeatObserver) {
        String url = buildSportsUrl(heartbeatUnsubscribeRequest.resourcePath());
        deleteRequest(url, heartbeatUnsubscribeRequest, heartbeatObserver, Heartbeat.class);
    }
}
