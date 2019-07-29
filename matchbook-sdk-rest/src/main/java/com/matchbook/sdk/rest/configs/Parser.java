package com.matchbook.sdk.rest.configs;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;

public interface Parser extends AutoCloseable {

    void startObject() throws IOException;

    void startArray() throws IOException;

    void moveToNext() throws IOException;

    boolean hasNext();

    String getFieldName() throws IOException;

    Boolean getBoolean() throws IOException;

    String getString() throws IOException;

    Integer getInteger() throws IOException;

    Long getLong() throws IOException;

    BigDecimal getDecimal() throws IOException;

    Instant getInstant() throws IOException;

    <T extends Enum<T>> Enum<T> getEnum(Class<T> enumClass) throws IOException;

}
