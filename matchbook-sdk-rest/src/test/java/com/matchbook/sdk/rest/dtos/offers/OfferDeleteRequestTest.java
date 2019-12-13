package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestRequestTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OfferDeleteRequestTest extends RestRequestTest<OfferDeleteRequest> {

    private Long offerId;

    @Override
    @BeforeEach
    protected void setUp() {
        offerId = 425096769380013L;

        super.setUp();
    }

    @Override
    protected OfferDeleteRequest newRequest() {
        return new OfferDeleteRequest.Builder(offerId).build();
    }

    @Test
    @DisplayName("Check offer ID")
    void offerIdTest() {
        assertThat(unit.getOfferId()).isEqualTo(offerId);
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("v2/offers/" + offerId);
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).isEmpty();
    }

}
