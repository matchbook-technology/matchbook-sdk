package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.rest.dtos.offers.CancelledMatchedBetsResponse;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;
import com.matchbook.sdk.rest.readers.PageableResponseReader;

public class CancelledMatchedBetsReader extends PageableResponseReader<MatchedBet, CancelledMatchedBetsResponse> {

    public CancelledMatchedBetsReader() {
        super(new MatchedBetReader());
    }

    @Override
    protected CancelledMatchedBetsResponse newPageableResponse() {
        return new CancelledMatchedBetsResponse();
    }

    @Override
    protected String itemsFieldName() {
        return "matched-bets";
    }

}
