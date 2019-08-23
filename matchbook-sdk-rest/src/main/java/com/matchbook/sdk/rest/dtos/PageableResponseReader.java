package com.matchbook.sdk.rest.dtos;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;

public abstract class PageableResponseReader<T, R extends PageableResponse<T>> implements Reader<T, R> {

    protected Parser parser;
    private ReadingItemStatus readingItemStatus;

    abstract protected String getItemsFieldName();

    abstract protected T readItem() throws MatchbookSDKParsingException;

    protected PageableResponseReader() {
        parser = null;
        readingItemStatus = ReadingItemStatus.NOT_READ;
    }

    @Override
    public void startReading(Parser parser) throws MatchbookSDKParsingException {
        this.parser = parser;
        parser.moveToNextToken();
    }

    @Override
    public boolean hasMoreItem() {
        return readingItemStatus != ReadingItemStatus.READ;
    }

    @Override
    public T readNextItem() throws MatchbookSDKParsingException {
        if (readingItemStatus == ReadingItemStatus.READ) {
            return null;
        } else if (readingItemStatus == ReadingItemStatus.NOT_READ) {
            skipToItems();
            readingItemStatus = ReadingItemStatus.READING;
        }

        parser.moveToNextToken();
        T item = readItem();
        parser.moveToNextToken();

        if (parser.isEndOfArray()) {
            readingItemStatus = ReadingItemStatus.READ;
        }
        return item;
    }

    private void skipToItems() throws MatchbookSDKParsingException {
        while (!parser.isEndOfBlock() && !getItemsFieldName().equals(parser.getFieldName())) {
            parser.moveToNextValue();
        }
    }

    private enum ReadingItemStatus {
        NOT_READ, READING, READ;
    }

}
