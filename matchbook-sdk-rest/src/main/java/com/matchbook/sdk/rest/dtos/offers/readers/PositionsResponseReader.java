package com.matchbook.sdk.rest.dtos.offers.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.offers.Position;
import com.matchbook.sdk.rest.dtos.offers.PositionsResponse;

public class PositionsResponseReader extends PageableResponseReader<Position, PositionsResponse> {

    public PositionsResponseReader() {
        super(new PositionResponseReader());
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
