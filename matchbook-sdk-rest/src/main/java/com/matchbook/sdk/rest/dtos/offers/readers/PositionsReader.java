package com.matchbook.sdk.rest.dtos.offers.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.offers.Position;
import com.matchbook.sdk.rest.dtos.offers.PositionsResponse;

public class PositionsReader extends PageableResponseReader<Position, PositionsResponse> {

    public PositionsReader() {
        super(new PositionReader());
    }

    @Override
    protected PositionsResponse newPageableResponse() {
        return new PositionsResponse();
    }

    @Override
    protected String itemsFieldName() {
        return "positions";
    }

}
