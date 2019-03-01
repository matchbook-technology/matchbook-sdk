package com.matchbook.sdk.clients.rest.dtos.events;

import com.matchbook.sdk.clients.rest.dtos.MatchbookPageableRequest;

public class SportsRequest extends MatchbookPageableRequest {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
