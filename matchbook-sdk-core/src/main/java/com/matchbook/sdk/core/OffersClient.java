package com.matchbook.sdk.core;

import com.matchbook.sdk.common.StreamObserver;
import com.matchbook.sdk.core.dtos.offers.AggregatedMatchedBet;
import com.matchbook.sdk.core.dtos.offers.AggregatedMatchedBetsRequest;
import com.matchbook.sdk.core.dtos.offers.CancelledMatchedBetsRequest;
import com.matchbook.sdk.core.dtos.offers.MatchedBet;
import com.matchbook.sdk.core.dtos.offers.Offer;
import com.matchbook.sdk.core.dtos.offers.OfferDeleteRequest;
import com.matchbook.sdk.core.dtos.offers.OfferEdit;
import com.matchbook.sdk.core.dtos.offers.OfferEditGetRequest;
import com.matchbook.sdk.core.dtos.offers.OfferEditsGetRequest;
import com.matchbook.sdk.core.dtos.offers.OfferGetRequest;
import com.matchbook.sdk.core.dtos.offers.OfferPutRequest;
import com.matchbook.sdk.core.dtos.offers.OffersDeleteRequest;
import com.matchbook.sdk.core.dtos.offers.OffersGetRequest;
import com.matchbook.sdk.core.dtos.offers.OffersPostRequest;
import com.matchbook.sdk.core.dtos.offers.OffersPutRequest;
import com.matchbook.sdk.core.dtos.offers.Position;
import com.matchbook.sdk.core.dtos.offers.PositionsRequest;

public interface OffersClient extends Client {

    void getOffer(OfferGetRequest offerGetRequest, StreamObserver<Offer> offerObserver);

    void getOffers(OffersGetRequest offersGetRequest, StreamObserver<Offer> offersObserver);

    void getOfferEdit(OfferEditGetRequest offerEditGetRequest, StreamObserver<OfferEdit> offerEditObserver);

    void getOfferEdits(OfferEditsGetRequest offerEditsGetRequest, StreamObserver<OfferEdit> offerEditsObserver);

    void getPositions(PositionsRequest positionsRequest, StreamObserver<Position> positionsRequestObserver);

    void getAggregatedMatchedBets(AggregatedMatchedBetsRequest aggregatedMatchedBetsRequest,
            StreamObserver<AggregatedMatchedBet> aggregatedMatchedBetsObserver);

    void getCancelledMatchedBets(CancelledMatchedBetsRequest cancelledMatchedBetsRequest,
            StreamObserver<MatchedBet> cancelledMatchedBetsObserver);

    void submitOffers(OffersPostRequest offersPostRequest, StreamObserver<Offer> offersObserver);

    void editOffer(OfferPutRequest offerPutRequest, StreamObserver<Offer> offerObserver);

    void editOffers(OffersPutRequest offersPutRequest, StreamObserver<Offer> offersObserver);

    void cancelOffer(OfferDeleteRequest offerDeleteRequest, StreamObserver<Offer> offerObserver);

    void cancelOffers(OffersDeleteRequest offersDeleteRequest, StreamObserver<Offer> offersObserver);

}
