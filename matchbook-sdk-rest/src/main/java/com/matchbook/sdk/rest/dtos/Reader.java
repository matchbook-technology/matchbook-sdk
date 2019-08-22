package com.matchbook.sdk.rest.dtos;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;

public interface Reader<T, R extends RestResponse<T>> {

    void startReading(Parser parser) throws MatchbookSDKParsingException;

    T readNextItem() throws MatchbookSDKParsingException;

    R readFull() throws MatchbookSDKParsingException;

}
