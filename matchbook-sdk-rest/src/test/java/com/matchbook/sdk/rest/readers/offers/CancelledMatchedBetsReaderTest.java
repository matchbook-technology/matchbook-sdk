package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.rest.dtos.offers.CancelledMatchedBetsResponse;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;
import com.matchbook.sdk.rest.readers.PageableResponseReaderTest;

import org.mockito.Mock;

class CancelledMatchedBetsReaderTest
        extends PageableResponseReaderTest<CancelledMatchedBetsReader, MatchedBet, CancelledMatchedBetsResponse> {

    @Mock
    private MatchedBetReader matchedBetReader;

    @Mock
    private MatchedBet matchedBet;

    @Override
    protected CancelledMatchedBetsReader newReader() {
        return new CancelledMatchedBetsReader(matchedBetReader);
    }

    @Override
    protected MatchedBetReader getItemsReader() {
        return matchedBetReader;
    }

    @Override
    protected MatchedBet getItem() {
        return matchedBet;
    }

    @Override
    protected String getItemsFieldName() {
        return "matched-bets";
    }

}
