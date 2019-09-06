package com.matchbook.sdk.rest.dtos;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;

public abstract class ResponseReader<T extends RestResponse<T>> implements Reader<T, T> {

    protected Parser parser;
    private ReadingItemStatus readingItemStatus;

    abstract protected T readItem() throws MatchbookSDKParsingException;

    protected ResponseReader() {
        parser = null;
    }

    @Override
    public void startReading(Parser parser) throws MatchbookSDKParsingException {
        this.parser = parser;
        readingItemStatus = ReadingItemStatus.NOT_READ;
    }

    @Override
    public boolean hasMoreItems() {
        return readingItemStatus != ReadingItemStatus.READ;
    }

    @Override
    public T readNextItem() throws MatchbookSDKParsingException {
        if (readingItemStatus == ReadingItemStatus.READ) {
            return null;
        }

        T item = readItem();
        readingItemStatus = ReadingItemStatus.READ;
        return item;
    }

    @Override
    public T readFull() throws MatchbookSDKParsingException {
        return readNextItem();
    }

    private enum ReadingItemStatus {
        NOT_READ, READ;
    }

}
