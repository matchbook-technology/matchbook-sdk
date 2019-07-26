package com.matchbook.sdk.rest.dtos;

import java.util.Map;

public interface RestRequest {

    String resourcePath();

    Map<String, String> parameters();

}
