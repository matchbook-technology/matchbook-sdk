package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBet;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBetsRequest;
import com.matchbook.sdk.rest.dtos.offers.CancelledMatchedBetsRequest;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;
import com.matchbook.sdk.rest.dtos.offers.Offer;
import com.matchbook.sdk.rest.dtos.offers.OfferDeleteRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferEditsGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferPutRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersDeleteRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersPostRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersPutRequest;
import com.matchbook.sdk.rest.dtos.offers.Position;
import com.matchbook.sdk.rest.dtos.offers.PositionsRequest;

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
