package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.PageableRequestTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OfferEditsGetRequestTest extends PageableRequestTest<OfferEditsGetRequest> {

    private Long offerId;

    @Override
    @BeforeEach
    protected void setUp() {
        offerId = 382937981320019L;

        super.setUp();
    }

    @Override
    protected OfferEditsGetRequest newPageableRequest(int offset, int perPage) {
        return new OfferEditsGetRequest.Builder(offerId)
                .offset(offset)
                .perPage(perPage)
                .build();
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
        assertThat(path).isEqualTo("v2/offers/" + offerId + "/offer-edits");
    }

}
