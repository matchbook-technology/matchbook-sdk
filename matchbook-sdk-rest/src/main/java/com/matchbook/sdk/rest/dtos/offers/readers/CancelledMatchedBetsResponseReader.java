package com.matchbook.sdk.rest.dtos.offers.readers;

import com.matchbook.sdk.rest.dtos.PageableResponseReader;
import com.matchbook.sdk.rest.dtos.offers.CancelledMatchedBetsResponse;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;

public class CancelledMatchedBetsResponseReader extends PageableResponseReader<MatchedBet, CancelledMatchedBetsResponse> {

    public CancelledMatchedBetsResponseReader() {
        super(new MatchedBetResponseReader());
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
