package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.matchbook.sdk.rest.dtos.RestRequestTest;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OfferGetRequestTest extends RestRequestTest<OfferGetRequest> {

    private Long offerId;

    @Override
    @BeforeEach
    protected void setUp() {
        offerId = 425096769380013L;

        super.setUp();
    }

    @Override
    protected OfferGetRequest newRequest() {
        return new OfferGetRequest.Builder(offerId)
                .includeEdits(true)
                .build();
    }

    @Override
    protected OfferGetRequest newEmptyRequest() {
        return new OfferGetRequest.Builder(offerId).build();
    }

    @Test
    @DisplayName("Check offer ID")
    void offerIdTest() {
        assertThat(unit.getOfferId()).isEqualTo(offerId)
                .isEqualTo(emptyUnit.getOfferId());
    }

    @Test
    @DisplayName("Check include offer edits")
    void includeEditsTest() {
        assertThat(unit.includeEdits()).isTrue();
        assertThat(emptyUnit.includeEdits()).isFalse();
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
        assertThat(unit.parameters())
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(tuple("include-edits", "true"));

        assertThat(emptyUnit.parameters())
                .extractingFromEntries(Map.Entry::getKey, Map.Entry::getValue)
                .contains(tuple("include-edits", "false"));
    }

}
