package com.matchbook.sdk.rest.dtos.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.RestRequestTest;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OffersPostRequestTest extends RestRequestTest<OffersPostRequest> {

    private OddsType oddsType;
    private ExchangeType exchangeType;
    private List<OfferPostRequest> offers;

    @Override
    @BeforeEach
    protected void setUp() {
        oddsType = OddsType.INDO;
        exchangeType = ExchangeType.BINARY;
        OfferPostRequest offer1 = mock(OfferPostRequest.class);
        OfferPostRequest offer2 = mock(OfferPostRequest.class);
        offers = Arrays.asList(offer1, offer2);

        super.setUp();
    }

    @Override
    protected OffersPostRequest newRequest() {
        return new OffersPostRequest.Builder(oddsType, exchangeType, offers).build();
    }

    @Test
    @DisplayName("Check odds type")
    void oddsTypeTest() {
        assertThat(unit.getOddsType()).isEqualTo(oddsType);
    }

    @Test
    @DisplayName("Check exchange type")
    void exchangeTypeTest() {
        assertThat(unit.getExchangeType()).isEqualTo(exchangeType);
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
