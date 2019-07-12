package com.matchbook.sdk.core;

public interface Client extends AutoCloseable {

    @Override
    void close();
}
