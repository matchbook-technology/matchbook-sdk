package com.matchbook.sdk.rest.configs;

import java.math.BigDecimal;
import java.time.Instant;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;

public interface Parser extends AutoCloseable {

    boolean hasNext();

    void startObject() throws MatchbookSDKParsingException;

    void startArray() throws MatchbookSDKParsingException;

    void moveToNext() throws MatchbookSDKParsingException;

    String getFieldName() throws MatchbookSDKParsingException;

    Boolean getBoolean() throws MatchbookSDKParsingException;

    String getString() throws MatchbookSDKParsingException;

    Integer getInteger() throws MatchbookSDKParsingException;

    Long getLong() throws MatchbookSDKParsingException;

    BigDecimal getDecimal() throws MatchbookSDKParsingException;

    Instant getInstant() throws MatchbookSDKParsingException;

    <T extends Enum<T>> Enum<T> getEnum(Class<T> enumClass) throws MatchbookSDKParsingException;

}
