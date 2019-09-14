package com.matchbook.sdk.rest.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.dtos.RestResponse;

public interface Reader<T, R extends RestResponse> {

    void startReading(Parser parser) throws MatchbookSDKParsingException;

    T readNextItem() throws MatchbookSDKParsingException;

    boolean hasMoreItems();

    default R readFullResponse() throws MatchbookSDKParsingException {
        throw new UnsupportedOperationException("This reader does not support parsing the full message.");
    }

}
