/*
 * Copyright (c) 2019 Triplebet Limited. All right reserved. Inchalla, Le Val, Alderney, GY9 3UL.
 * Company Registration Number: 1827.
 */

package com.matchbook.sdk.core.clients.rest.dtos.offers;

import com.matchbook.sdk.core.clients.rest.dtos.MatchbookPageableResponse;

public class AggregatedMatchedBetsResponse extends MatchbookPageableResponse<AggregatedMatchedBet> {

    @Override
    public String toString() {
        return AggregatedMatchedBetsResponse.class.getSimpleName() + " {" +
                "total=" + total +
                ", offset=" + offset +
                ", perPage=" + perPage +
                ", matchedBets=" + content +
                "}";
    }

}
