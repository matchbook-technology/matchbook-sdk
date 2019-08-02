package com.matchbook.sdk.rest.dtos;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;

public interface Reader<R extends RestResponse<T>, T> {

    void startReading(Parser parser) throws MatchbookSDKParsingException;

    boolean hasMoreItems() throws MatchbookSDKParsingException;

    T readNextItem() throws MatchbookSDKParsingException;

    R readFull() throws MatchbookSDKParsingException;

}
