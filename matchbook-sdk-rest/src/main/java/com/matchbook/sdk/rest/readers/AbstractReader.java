package com.matchbook.sdk.rest.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.dtos.RestResponse;

public abstract class AbstractReader<T, R extends RestResponse> implements Reader<T, R> {

    protected Parser parser;
    protected ReadingItemsStatus readingItemStatus;

    protected AbstractReader() {
        parser = null;
    }

    @Override
    public void init(Parser parser) throws MatchbookSDKParsingException {
        this.parser = parser;

        parser.moveToNextToken();
        readingItemStatus = parser.hasCurrentToken() ? ReadingItemsStatus.NOT_READ : ReadingItemsStatus.READ;
    }

    @Override
    public boolean hasMoreItems() {
        return readingItemStatus != ReadingItemsStatus.READ;
    }

    protected enum ReadingItemsStatus {
        NOT_READ, READING, READ;
    }

}
