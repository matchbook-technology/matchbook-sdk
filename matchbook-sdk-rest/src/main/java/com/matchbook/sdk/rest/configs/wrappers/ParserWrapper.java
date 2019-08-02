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
import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;

public class ParserWrapper implements Parser {

    private final JsonParser jsonParser;
    private final Queue<JsonToken> terminationTokens;

    public ParserWrapper(JsonFactory jsonFactory, InputStream inputStream) throws MatchbookSDKParsingException {
        try {
            this.jsonParser = jsonFactory.createParser(inputStream);
        } catch (IOException e) {
            throw new MatchbookSDKParsingException("Unable to create parser.", e);
        }
        terminationTokens = new LinkedList<>();
    }

    @Override
    public boolean hasNext() {
        if (terminationTokens.isEmpty() || jsonParser.hasToken(terminationTokens.peek())) {
            terminationTokens.remove();
            return false;
        }
        return true;
    }

    @Override
    public void startObject() throws MatchbookSDKParsingException {
        if (jsonParser.isExpectedStartObjectToken()) {
            terminationTokens.add(JsonToken.END_OBJECT);
        } else {
            throw new MatchbookSDKParsingException(String.format("Unexpected token encountered! Start of object expected ('%s'), but found '%s'.",
                    JsonToken.START_OBJECT.asString(), jsonParser.getCurrentToken().asString()));
        }
    }

    @Override
    public void startArray() throws MatchbookSDKParsingException {
        if (jsonParser.isExpectedStartArrayToken()) {
            terminationTokens.add(JsonToken.END_ARRAY);
        } else {
            throw new MatchbookSDKParsingException(String.format("Unexpected token encountered! Start of array expected ('%s'), but found '%s'.",
                    JsonToken.START_ARRAY.asString(), jsonParser.getCurrentToken().asString()));
        }
    }

    @Override
    public void moveToNext() throws MatchbookSDKParsingException {
        try {
            jsonParser.nextToken();
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
