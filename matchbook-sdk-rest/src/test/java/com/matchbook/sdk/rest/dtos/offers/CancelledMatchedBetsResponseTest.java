package com.matchbook.sdk.rest.dtos.offers;

import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.PageableResponseTest;
import com.matchbook.sdk.rest.dtos.PartiallyFailableResponseTest;

class CancelledMatchedBetsResponseTest extends PageableResponseTest<CancelledMatchedBetsResponse, MatchedBet> {

    @Override
    protected CancelledMatchedBetsResponse newPageableResponse() {
        return new CancelledMatchedBetsResponse();
    }

    @Override
    protected MatchedBet mockItem() {
        return mock(MatchedBet.class);
    }

}
