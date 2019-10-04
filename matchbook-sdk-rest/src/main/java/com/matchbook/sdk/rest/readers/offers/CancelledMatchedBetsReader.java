package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.rest.readers.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.offers.CancelledMatchedBetsResponse;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;

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
