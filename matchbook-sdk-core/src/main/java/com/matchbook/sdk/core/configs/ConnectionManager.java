package com.matchbook.sdk.core.configs;

import com.matchbook.sdk.core.configs.wrappers.HttpClient;
import com.matchbook.sdk.core.configs.wrappers.Serializer;

public interface ConnectionManager {

    ClientConfig getClientConfig();

    HttpClient getHttpClient();

    Serializer getSerializer();

}
