package com.matchbook.sdk.core.clients.rest.dtos;

import java.util.Map;

public interface RestRequest {

    String resourcePath();

    Map<String, String> parameters();

}
