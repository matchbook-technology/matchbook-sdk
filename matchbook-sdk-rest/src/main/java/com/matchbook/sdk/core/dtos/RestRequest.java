package com.matchbook.sdk.core.dtos;

import java.util.Map;

public interface RestRequest {

    String resourcePath();

    Map<String, String> parameters();

}
