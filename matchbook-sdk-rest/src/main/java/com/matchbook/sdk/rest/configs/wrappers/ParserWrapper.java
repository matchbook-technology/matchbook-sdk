package com.matchbook.sdk.rest.configs.wrappers;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;

public class ParserWrapper implements Parser {

    private final JsonParser jsonParser;

    public ParserWrapper(JsonFactory jsonFactory, InputStream inputStream) throws MatchbookSDKParsingException {
        try {
            this.jsonParser = jsonFactory.createParser(inputStream);
        } catch (IOException e) {
            throw new MatchbookSDKParsingException("Unable to create parser.", e);
        }
    }

    @Override
    public boolean isStartOfObject() {
        return jsonParser.hasToken(JsonToken.START_OBJECT);
    }

    @Override
    public boolean isStartOfArray() {
        return jsonParser.hasToken(JsonToken.START_ARRAY);
    }

    @Override
    public boolean isStartOfBlock() {
        return isStartOfObject() || isStartOfArray();
    }

    @Override
    public boolean isEndOfObject() {
        return jsonParser.hasToken(JsonToken.END_OBJECT);
    }

    @Override
    public boolean isEndOfArray() {
        return jsonParser.hasToken(JsonToken.END_ARRAY);
    }

    @Override
    public boolean isEndOfBlock() {
        return isEndOfObject() || isEndOfArray();
    }

    @Override
    public void moveToNextToken() throws MatchbookSDKParsingException {
        try {
            jsonParser.nextToken();
        } catch (IOException e) {
            throw new MatchbookSDKParsingException(e);
        }
    }

    @Override
    public void moveToNextValue() throws MatchbookSDKParsingException {
        try {
            jsonParser.nextValue();
        } catch (IOException e) {
            throw new MatchbookSDKParsingException(e);
        }
    }

    @Override
    public String getFieldName() throws MatchbookSDKParsingException {
        try {
            return jsonParser.getCurrentName();
        } catch (IOException e) {
            throw new MatchbookSDKParsingException(e);
        }
    }

    @Override
    public Boolean getBoolean() throws MatchbookSDKParsingException {
        if (isNotNullValue()) {
            try {
                return jsonParser.getValueAsBoolean();
            } catch (IOException e) {
                throw new MatchbookSDKParsingException(e);
            }
        }
        return null;
    }

    @Override
    public String getString() throws MatchbookSDKParsingException {
        if (isNotNullValue()) {
            try {
                return jsonParser.getValueAsString();
            } catch (IOException e) {
                throw new MatchbookSDKParsingException(e);
            }
        }
        return null;
    }

    @Override
    public Integer getInteger() throws MatchbookSDKParsingException {
        if (isNotNullValue()) {
            try {
                return jsonParser.getValueAsInt();
            } catch (IOException e) {
                throw new MatchbookSDKParsingException(e);
            }
        }
        return null;
    }

    @Override
    public Long getLong() throws MatchbookSDKParsingException {
        if (isNotNullValue()) {
            try {
                return jsonParser.getValueAsLong();
            } catch (IOException e) {
                throw new MatchbookSDKParsingException(e);
            }
        }
        return null;
    }

    @Override
    public BigDecimal getDecimal() throws MatchbookSDKParsingException {
        if (isNotNullValue()) {
            try {
                return jsonParser.getDecimalValue();
            } catch (IOException e) {
                throw new MatchbookSDKParsingException(e);
            }
        }
        return null;
    }

    @Override
    public Instant getInstant() throws MatchbookSDKParsingException {
        if (isNotNullValue()) {
            try {
                return Instant.parse(jsonParser.getValueAsString());
            } catch (IOException e) {
                throw new MatchbookSDKParsingException(e);
            }
        }
        return null;
    }

    @Override
    public <T extends Enum<T>> Enum<T> getEnum(Class<T> enumClass) throws MatchbookSDKParsingException {
        if (isNotNullValue()) {
            try {
                String value = jsonParser.getValueAsString().toUpperCase().replace('-', '_');
                return Enum.valueOf(enumClass, value);
            } catch (IOException e) {
                throw new MatchbookSDKParsingException(e);
            }
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
