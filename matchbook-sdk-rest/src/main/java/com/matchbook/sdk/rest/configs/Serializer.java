package com.matchbook.sdk.rest.configs;

import java.io.IOException;
import java.io.InputStream;

public interface Serializer {

    <T> String writeObjectAsString(T object) throws IOException;

    <T> T readObject(InputStream inputStream, Class<T> type) throws IOException;

}
