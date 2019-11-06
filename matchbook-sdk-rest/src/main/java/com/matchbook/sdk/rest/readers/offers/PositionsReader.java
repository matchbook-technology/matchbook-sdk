package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.dtos.offers.Position;
import com.matchbook.sdk.rest.dtos.offers.PositionsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReader;

public class PositionsReader extends PageableResponseReader<Position, PositionsResponse> {

    public PositionsReader() {
        super(new PositionReader());
    }

    @VisibleForTesting
    PositionsReader(PositionReader positionReader) {
        super(positionReader);
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
