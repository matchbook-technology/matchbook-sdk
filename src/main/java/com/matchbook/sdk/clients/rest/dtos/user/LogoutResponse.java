package com.matchbook.sdk.clients.rest.dtos.user;

import com.matchbook.sdk.clients.rest.dtos.MatchbookResponse;

public class LogoutResponse implements MatchbookResponse {

    private String sessionToken;
    private Long userId;
    private String username;
}
