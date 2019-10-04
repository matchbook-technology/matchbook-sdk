package com.matchbook.sdk.rest.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.RestResponse;

public abstract class ResponseReader<T extends RestResponse> extends AbstractReader<T, T> {

    abstract protected T readItem() throws MatchbookSDKParsingException;

    @Override
    public T readNextItem() throws MatchbookSDKParsingException {
        if (readingItemStatus == ReadingItemsStatus.NOT_READ) {
            T item = readItem();
            readingItemStatus = ReadingItemsStatus.READ;
            return item;
        }
        return null;
    }

    @Override
    public T readFullResponse() throws MatchbookSDKParsingException {
        return readNextItem();
    }

}
