package com.matchbook.sdk.rest.dtos;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;

public abstract class AbstractReader<T, R extends RestResponse<T>> implements Reader<T, R> {

    protected Parser parser;
    private ReadingItemStatus readingItemStatus;

    abstract protected String getItemsFieldName();

    abstract protected T readItem() throws MatchbookSDKParsingException;

    protected AbstractReader() {
        parser = null;
        readingItemStatus = ReadingItemStatus.NOT_READ;
    }

    @Override
    public void startReading(Parser parser) throws MatchbookSDKParsingException {
        this.parser = parser;
        parser.startObject();
    }

    @Override
    public boolean hasMoreItems() throws MatchbookSDKParsingException {
        if (readingItemStatus == ReadingItemStatus.NOT_READ) {
            return true;
        } else if (readingItemStatus == ReadingItemStatus.READ) {
            return false;
        } else {
            boolean hasMoreItems = parser.hasNext();
            if (!hasMoreItems) {
                readingItemStatus = ReadingItemStatus.READ;
            }
            return hasMoreItems;
        }
    }

    @Override
    public T readNextItem() throws MatchbookSDKParsingException {
        if (readingItemStatus == ReadingItemStatus.NOT_READ) {
            skipToItems();
        }
        return readItem();
    }

    private void skipToItems() throws MatchbookSDKParsingException {
        while (parser.hasNext() && !getItemsFieldName().equals(parser.getFieldName())) {
            parser.moveToNext();
        }
        readingItemStatus = ReadingItemStatus.READING;
        parser.startArray();
    }

    private enum ReadingItemStatus {
        NOT_READ, READING, READ;
    }

}
