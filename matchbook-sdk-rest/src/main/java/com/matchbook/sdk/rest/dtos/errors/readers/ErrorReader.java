package com.matchbook.sdk.rest.dtos.errors.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.ResponseReader;
import com.matchbook.sdk.rest.dtos.errors.Error;

import java.util.ArrayList;
import java.util.List;

public class ErrorReader extends ResponseReader<Error> {

    public ErrorReader() {
        super();
    }

    @Override
    protected Error readItem() throws MatchbookSDKParsingException {
        final Error error = new Error();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("field".equals(fieldName)) {
                error.setField(parser.getString());
            } else if ("messages".equals(fieldName)) {
                List<String> messages = readMessages();
                error.setMessages(messages);
            }
            parser.moveToNextToken();
        }
        return error;
    }

    private List<String> readMessages() {
        List<String> messages = new ArrayList<>(1);
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            String message = parser.getString();
            messages.add(message);
            parser.moveToNextToken();
        }
        return messages;
    }

}
