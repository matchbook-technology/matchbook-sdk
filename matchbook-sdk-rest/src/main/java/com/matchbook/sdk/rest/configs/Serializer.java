package com.matchbook.sdk.rest.configs;

import java.io.IOException;
import java.io.InputStream;

public interface Serializer {

    <T> String writeObjectAsString(T object) throws IOException;

    Parser newParser(InputStream inputStream) throws IOException;

}
