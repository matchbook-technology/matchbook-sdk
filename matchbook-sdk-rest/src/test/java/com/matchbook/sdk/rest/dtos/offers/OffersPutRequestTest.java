package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.RestRequestTest;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OffersPutRequestTest extends RestRequestTest<OffersPutRequest> {

    private List<OfferPutRequest> offers;

    @Override
    @BeforeEach
    protected void setUp() {
        OfferPutRequest offer1 = mock(OfferPutRequest.class);
        OfferPutRequest offer2 = mock(OfferPutRequest.class);
        offers = Arrays.asList(offer1, offer2);

        super.setUp();
    }

    @Override
    protected OffersPutRequest newRequest() {
        return new OffersPutRequest.Builder(offers).build();
    }

    @Test
    @DisplayName("Check offers")
    void offersTest() {
        assertThat(unit.getOffers()).containsExactlyInAnyOrderElementsOf(offers);
    }

    @Test
    @DisplayName("Verify resource path")
    void resourcePathTest() {
        String path = unit.resourcePath();
        assertThat(path).isEqualTo("v2/offers");
    }

    @Test
    @DisplayName("Check query parameters")
    void parametersTest() {
        assertThat(unit.parameters()).isEmpty();
    }

}
