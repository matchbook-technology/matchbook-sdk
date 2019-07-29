package com.matchbook.sdk.rest.configs.wrappers;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.matchbook.sdk.rest.configs.Parser;

public class ParserWrapper implements Parser {

    private final JsonParser jsonParser;

    private Queue<JsonToken> terminationTokens;

    public ParserWrapper(JsonFactory jsonFactory, InputStream inputStream) throws IOException {
        this.jsonParser = jsonFactory.createParser(inputStream);
        terminationTokens = new LinkedList<>();
    }

    @Override
    public void startObject() throws IOException {
        if (jsonParser.isExpectedStartObjectToken()) {
            terminationTokens.add(JsonToken.END_OBJECT);
        } else {
            throw new IOException(String.format("Unexpected token encountered! Start of object expected ('%s'), but found '%s'.",
                    JsonToken.START_OBJECT.asString(), jsonParser.getCurrentToken().asString()));
        }
    }

    @Override
    public void startArray() throws IOException {
        if (jsonParser.isExpectedStartArrayToken()) {
            terminationTokens.add(JsonToken.END_ARRAY);
        } else {
            throw new IOException(String.format("Unexpected token encountered! Start of array expected ('%s'), but found '%s'.",
                    JsonToken.START_ARRAY.asString(), jsonParser.getCurrentToken().asString()));
        }
    }

    @Override
    public void moveToNext() throws IOException {
        jsonParser.nextToken();
    }

    @Override
    public boolean hasNext() {
        if (!terminationTokens.isEmpty() && jsonParser.hasToken(terminationTokens.peek())) {
            terminationTokens.remove();
            return false;
        }
        return true;
    }

    @Override
    public String getFieldName() throws IOException {
        return jsonParser.getCurrentName();
    }

    @Override
    public Boolean getBoolean() throws IOException {
        return isNotNullValue() ? jsonParser.getValueAsBoolean() : null;
    }

    @Override
    public String getString() throws IOException {
        return isNotNullValue() ? jsonParser.getValueAsString() : null;
    }

    @Override
    public Integer getInteger() throws IOException {
        return isNotNullValue() ? jsonParser.getValueAsInt() : null;
    }

    @Override
    public Long getLong() throws IOException {
        return isNotNullValue() ? jsonParser.getValueAsLong() : null;
    }

    @Override
    public BigDecimal getDecimal() throws IOException {
        return isNotNullValue() ? jsonParser.getDecimalValue() : null;
    }

    @Override
    public Instant getInstant() throws IOException {
        return isNotNullValue() ? Instant.parse(jsonParser.getValueAsString()) : null;
    }

    @Override
    public <T extends Enum<T>> Enum<T> getEnum(Class<T> enumClass) throws IOException {
        if (isNotNullValue()) {
            String value = jsonParser.getValueAsString().toUpperCase().replace('-', '_');
            return Enum.valueOf(enumClass, value);
        }
        return null;
    }

    private boolean isNotNullValue() {
        return jsonParser.hasCurrentToken() && !jsonParser.hasToken(JsonToken.VALUE_NULL);
    }

    @Override
    public void close() throws IOException {
        jsonParser.close();
    }

}
