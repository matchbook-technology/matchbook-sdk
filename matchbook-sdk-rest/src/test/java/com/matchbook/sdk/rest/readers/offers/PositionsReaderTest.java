package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.rest.dtos.offers.Position;
import com.matchbook.sdk.rest.dtos.offers.PositionsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReaderTest;

import org.mockito.Mock;

class PositionsReaderTest extends PageableResponseReaderTest<PositionsReader, Position, PositionsResponse> {

    @Mock
    private PositionReader positionReader;

    @Mock
    private Position position;

    @Override
    protected PositionsReader newReader() {
        return new PositionsReader(positionReader);
    }

    @Override
    protected PositionReader getItemsReader() {
        return positionReader;
    }

    @Override
    protected Position getItem() {
        return position;
    }

    @Override
    protected String getItemsFieldName() {
        return "positions";
    }

}
