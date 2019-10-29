package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OfferEditGetRequestTest extends RestRequestTest<OfferEditGetRequest> {

    private Long offerEditId;
    private Long offerId;

    @Override
    @BeforeEach
    protected void setUp() {
        offerEditId = 425098661640113L;;
        offerId = 425096769380013L;

        super.setUp();
    }

    @Override
    protected OfferEditGetRequest newRequest() {
        return new OfferEditGetRequest.Builder(offerEditId, offerId).build();
    }

    @Test
    @DisplayName("Check offer edit ID")
    void offerEditIdTest() {
        Long actualOfferEditId = unit.getOfferEditId();
        assertThat(actualOfferEditId).isEqualTo(offerEditId);
    }

    @Test
    @DisplayName("Check offer ID")
    void offerIdTest() {
        Long actualOfferId = unit.getOfferId();
        assertThat(actualOfferId).isEqualTo(offerId);
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("v2/offers/" + offerId + "/offer-edits/" + offerEditId);
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).isEmpty();
    }

}
