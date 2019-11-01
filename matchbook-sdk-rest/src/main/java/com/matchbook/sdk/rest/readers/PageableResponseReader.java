package com.matchbook.sdk.rest.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.PageableResponse;
import com.matchbook.sdk.rest.dtos.RestResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class PageableResponseReader<T extends RestResponse, R extends PageableResponse<T>> extends BaseReader<T, R> {

    final private ResponseReader<T> itemsReader;

    protected PageableResponseReader(ResponseReader<T> itemsReader) {
        this.itemsReader = itemsReader;
    }

    abstract protected R newPageableResponse();

    abstract protected String itemsFieldName();

    @Override
    public R readFullResponse() throws MatchbookSDKParsingException {
        final R pageableResponse = newPageableResponse();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("total".equals(fieldName)) {
                pageableResponse.setTotal(parser.getInteger());
            } else if ("offset".equals(fieldName)) {
                pageableResponse.setOffset(parser.getInteger());
            } else if ("per-page".equals(fieldName)) {
                pageableResponse.setPerPage(parser.getInteger());
            } else if (itemsFieldName().equals(fieldName)) {
                List<T> items = readItems();
                pageableResponse.setItems(items);
            }
            parser.moveToNextToken();
        }
        return pageableResponse;
    }

    protected List<T> readItems() {
        List<T> items = new ArrayList<>();
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            itemsReader.init(parser);
            T item = itemsReader.readFullResponse();
            if (Objects.nonNull(item)) {
                items.add(item);
            }
            parser.moveToNextToken();
        }
        return items;
    }

    @Override
    public T readNextItem() throws MatchbookSDKParsingException {
        if (readingItemStatus == ReadingItemsStatus.READ) {
            return null;
        } else if (readingItemStatus == ReadingItemsStatus.NOT_READ) {
            skipToItems();
            readingItemStatus = ReadingItemsStatus.READING;
        }

        itemsReader.init(parser);
        T item = itemsReader.readFullResponse();
        parser.moveToNextToken();
        if (parser.isEndOfArray()) {
            readingItemStatus = ReadingItemsStatus.READ;
        }
        return item;
    }

    private void skipToItems() throws MatchbookSDKParsingException {
        while (!parser.isEndOfObject() && !parser.isEndOfArray() && !itemsFieldName().equals(parser.getFieldName())) {
            parser.moveToNextValue();
        }
    }

}
