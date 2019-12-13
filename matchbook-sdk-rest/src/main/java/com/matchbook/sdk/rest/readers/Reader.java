package com.matchbook.sdk.rest.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.dtos.RestResponse;

public interface Reader<T, R extends RestResponse> {

    void init(Parser parser) throws MatchbookSDKParsingException;

    T readNextItem() throws MatchbookSDKParsingException;

    R readFullResponse() throws MatchbookSDKParsingException;

    boolean hasMoreItems();

}
