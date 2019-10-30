package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.configs.ConnectionManager;
import com.matchbook.sdk.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatGetRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatPostRequest;
import com.matchbook.sdk.rest.dtos.heartbeat.HeartbeatDeleteRequest;
import com.matchbook.sdk.rest.readers.heartbeat.HeartbeatReader;

public class HeartbeatClientRest extends BaseClientRest implements HeartbeatClient {

    public HeartbeatClientRest(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void getHeartbeat(HeartbeatGetRequest heartbeatGetRequest, StreamObserver<Heartbeat> heartbeatObserver) {
        String url = buildSportsUrl(heartbeatGetRequest.resourcePath());
        getRequest(url, heartbeatGetRequest, heartbeatObserver, new HeartbeatReader());
    }

    @Override
    public void sendHeartbeat(HeartbeatPostRequest heartbeatPostRequest, StreamObserver<Heartbeat> heartbeatObserver) {
        String url = buildSportsUrl(heartbeatPostRequest.resourcePath());
        postRequest(url, heartbeatPostRequest, heartbeatObserver, new HeartbeatReader());
    }

    @Override
    public void unsubscribeHeartbeat(HeartbeatDeleteRequest heartbeatDeleteRequest,
        StreamObserver<Heartbeat> heartbeatObserver) {
        String url = buildSportsUrl(heartbeatDeleteRequest.resourcePath());
        deleteRequest(url, heartbeatDeleteRequest, heartbeatObserver, new HeartbeatReader());
    }
}
