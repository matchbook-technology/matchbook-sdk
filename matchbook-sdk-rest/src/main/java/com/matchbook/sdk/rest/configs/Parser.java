package com.matchbook.sdk.rest.configs;

import java.math.BigDecimal;
import java.time.Instant;

public interface Parser extends AutoCloseable {

    boolean hasNext();

    void moveToNext();

    String getFieldName();

    Boolean getBoolean();

    String getText();

    Integer getInteger();

    Long getLong();

    BigDecimal getBigDecimal();

    Instant getInstant();

    <T extends Enum<T>> Enum<T> getEnum(Class<Enum<T>> enumClass);

}
