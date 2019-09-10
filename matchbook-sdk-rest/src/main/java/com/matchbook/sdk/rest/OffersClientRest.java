package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBet;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBetsRequest;
import com.matchbook.sdk.rest.dtos.offers.AggregatedMatchedBetsResponse;
import com.matchbook.sdk.rest.dtos.offers.CancelledMatchedBetsRequest;
import com.matchbook.sdk.rest.dtos.offers.CancelledMatchedBetsResponse;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;
import com.matchbook.sdk.rest.dtos.offers.Offer;
import com.matchbook.sdk.rest.dtos.offers.OfferDeleteRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferEditsGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferEditsResponse;
import com.matchbook.sdk.rest.dtos.offers.OfferGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OfferPutRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersDeleteRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersGetRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersPostRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersPutRequest;
import com.matchbook.sdk.rest.dtos.offers.OffersResponse;
import com.matchbook.sdk.rest.dtos.offers.Position;
import com.matchbook.sdk.rest.dtos.offers.PositionsRequest;
import com.matchbook.sdk.rest.dtos.offers.PositionsResponse;
import com.matchbook.sdk.rest.dtos.offers.readers.AggregatedMatchedBetsResponseReader;
import com.matchbook.sdk.rest.dtos.offers.readers.CancelledMatchedBetsResponseReader;
import com.matchbook.sdk.rest.dtos.offers.readers.PositionsResponseReader;

public class OffersClientRest extends AbstractRestClient implements OffersClient {

    public OffersClientRest(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public void getOffer(OfferGetRequest offerGetRequest, StreamObserver<Offer> offerObserver) {
        String url = buildSportsUrl(offerGetRequest.resourcePath());
        getRequest(url, offerGetRequest, offerObserver, Offer.class);
    }

    @Override
    public void getOffers(OffersGetRequest offersGetRequest, StreamObserver<Offer> offersObserver) {
        String url = buildSportsUrl(offersGetRequest.resourcePath());
        getRequest(url, offersGetRequest, offersObserver, OffersResponse.class);
    }

    @Override
    public void getOfferEdit(OfferEditGetRequest offerEditGetRequest, StreamObserver<OfferEdit> offerEditObserver) {
        String url = buildSportsUrl(offerEditGetRequest.resourcePath());
        getRequest(url, offerEditGetRequest, offerEditObserver, OfferEdit.class);
    }

    @Override
    public void getOfferEdits(OfferEditsGetRequest offerEditsGetRequest, StreamObserver<OfferEdit> offerEditsObserver) {
        String url = buildSportsUrl(offerEditsGetRequest.resourcePath());
        getRequest(url, offerEditsGetRequest, offerEditsObserver, OfferEditsResponse.class);
    }

    @Override
    public void getPositions(PositionsRequest positionsRequest, StreamObserver<Position> positionsRequestObserver) {
        String url = buildSportsUrl(positionsRequest.resourcePath());
        getRequest(url, positionsRequest, positionsRequestObserver, new PositionsResponseReader());
    }

    @Override
    public void getAggregatedMatchedBets(AggregatedMatchedBetsRequest aggregatedMatchedBetsRequest,
            StreamObserver<AggregatedMatchedBet> aggregatedMatchedBetsObserver) {
        String url = buildSportsUrl(aggregatedMatchedBetsRequest.resourcePath());
        getRequest(url, aggregatedMatchedBetsRequest, aggregatedMatchedBetsObserver, new AggregatedMatchedBetsResponseReader());
    }

    @Override
    public void getCancelledMatchedBets(CancelledMatchedBetsRequest cancelledMatchedBetsRequest,
            StreamObserver<MatchedBet> cancelledMatchedBetsObserver) {
        String url = buildSportsUrl(cancelledMatchedBetsRequest.resourcePath());
        getRequest(url, cancelledMatchedBetsRequest, cancelledMatchedBetsObserver, new CancelledMatchedBetsResponseReader());
    }

    @Override
    public void submitOffers(OffersPostRequest offersPostRequest, StreamObserver<Offer> offersObserver) {
        String url = buildSportsUrl(offersPostRequest.resourcePath());
        postRequest(url, offersPostRequest, offersObserver, Offer.class);
    }

    @Override
    public void editOffer(OfferPutRequest offerPutRequest, StreamObserver<Offer> offerObserver) {
        String url = buildSportsUrl(offerPutRequest.resourcePath());
        putRequest(url, offerPutRequest, offerObserver, Offer.class);
    }

    @Override
    public void editOffers(OffersPutRequest offersPutRequest, StreamObserver<Offer> offersObserver) {
        String url = buildSportsUrl(offersPutRequest.resourcePath());
        putRequest(url, offersPutRequest, offersObserver, Offer.class);
    }

    @Override
    public void cancelOffer(OfferDeleteRequest offerDeleteRequest, StreamObserver<Offer> offerObserver) {
        String url = buildSportsUrl(offerDeleteRequest.resourcePath());
        deleteRequest(url, offerDeleteRequest, offerObserver, Offer.class);
    }

    @Override
    public void cancelOffers(OffersDeleteRequest offersDeleteRequest, StreamObserver<Offer> offersObserver) {
        String url = buildSportsUrl(offersDeleteRequest.resourcePath());
        deleteRequest(url, offersDeleteRequest, offersObserver, Offer.class);
    }

}
