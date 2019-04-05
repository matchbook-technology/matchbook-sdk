package com.matchbook.sdk.core.configs;

public interface SessionManager {

    String sessionToken(char[] username, char[] password);

    void invalidateSessionToken();

}
