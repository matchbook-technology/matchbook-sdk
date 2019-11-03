package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.rest.dtos.events.Sport;
import com.matchbook.sdk.rest.dtos.events.SportsResponse;
import com.matchbook.sdk.rest.readers.PageableResponseReaderTest;

import org.mockito.Mock;

class SportsReaderTest extends PageableResponseReaderTest<SportsReader, Sport, SportsResponse> {

    @Mock
    private SportReader sportReader;

    @Mock
    private Sport sport;

    @Override
    protected SportsReader newReader() {
        return new SportsReader(sportReader);
    }

    @Override
    protected SportReader getItemsReader() {
        return sportReader;
    }

    @Override
    protected Sport getItem() {
        return sport;
    }

    @Override
    protected String getItemsFieldName() {
        return "sports";
    }

}
