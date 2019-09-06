package com.matchbook.sdk.rest.dtos;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.dtos.events.SportsResponse;

public interface Reader<T, R extends RestResponse<T>> {

    void startReading(Parser parser) throws MatchbookSDKParsingException;

    T readNextItem() throws MatchbookSDKParsingException;

    boolean hasMoreItems();

    default R readFull() throws MatchbookSDKParsingException {
        throw new UnsupportedOperationException("This reader does not support parsing the full message.");
    }

}
