package com.matchbook.sdk.rest.dtos;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;

public abstract class PageableResponseReader<T, R extends PageableResponse<T>> implements Reader<T, R> {

    protected Parser parser;
    private ReadingItemsStatus readingItemsStatus;

    abstract protected String getItemsFieldName();

    abstract protected T readItem() throws MatchbookSDKParsingException;

    protected PageableResponseReader() {
        parser = null;
    }

    @Override
    public void startReading(Parser parser) throws MatchbookSDKParsingException {
        this.parser = parser;
        readingItemsStatus = ReadingItemsStatus.NOT_READ;
        parser.moveToNextToken();
    }

    @Override
    public boolean hasMoreItems() {
        return readingItemsStatus != ReadingItemsStatus.READ;
    }

    @Override
    public T readNextItem() throws MatchbookSDKParsingException {
        if (readingItemsStatus == ReadingItemsStatus.READ) {
            return null;
        } else if (readingItemsStatus == ReadingItemsStatus.NOT_READ) {
            skipToItems();
            readingItemsStatus = ReadingItemsStatus.READING;
        }

        parser.moveToNextToken();
        T item = readItem();
        parser.moveToNextToken();

        if (parser.isEndOfArray()) {
            readingItemsStatus = ReadingItemsStatus.READ;
        }
        return item;
    }

    private void skipToItems() throws MatchbookSDKParsingException {
        while (!parser.isEndOfBlock() && !getItemsFieldName().equals(parser.getFieldName())) {
            parser.moveToNextValue();
        }
    }

    private enum ReadingItemsStatus {
        NOT_READ, READING, READ;
    }

}
