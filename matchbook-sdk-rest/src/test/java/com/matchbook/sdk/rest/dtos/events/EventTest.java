package com.matchbook.sdk.rest.dtos.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.RestResponseTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest extends RestResponseTest<Event> {

    @Override
    protected Event newResponse() {
        return new Event();
    }

    @Test
    @DisplayName("Set and get ID")
    void idTest() {
        Long id = 395729780570010L;
        unit.setId(id);
        assertThat(unit.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Set and get sport ID")
    void sportIdTest() {
        Long sportId = 15L;
        unit.setSportId(sportId);
        assertThat(unit.getSportId()).isEqualTo(sportId);
    }

    @Test
    @DisplayName("Set and get category IDs")
    void categoryIdsTest() {
        Long categoryId = 15L;
        unit.setCategoryIds(new Long[] {categoryId});
        assertThat(unit.getCategoryIds()).containsOnly(categoryId);
    }

    @Test
    @DisplayName("Set and get name")
    void nameTest() {
        String name = "CSKA Moscow vs Bayer 04 Leverkusen";
        unit.setName(name);
        assertThat(unit.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("Set and get status")
    void eventStatusTest() {
        EventStatus status = EventStatus.SUSPENDED;
        unit.setStatus(status);
        assertThat(unit.getStatus()).isEqualTo(status);
    }

    @Test
    @DisplayName("Set and get start time")
    void startTest() {
        Instant startTime = Instant.now();
        unit.setStart(startTime);
        assertThat(unit.getStart()).isCloseTo(startTime, within(1L, ChronoUnit.SECONDS));
    }

    @Test
    @DisplayName("Set and get in running")
    void inRunningTest() {
        unit.setInRunning(true);
        assertThat(unit.isInRunning()).isTrue();
    }

    @Test
    @DisplayName("Set and get allow live betting")
    void allowLiveBettingTest() {
        unit.setAllowLiveBetting(true);
        assertThat(unit.isAllowLiveBetting()).isTrue();
    }

    @Test
    @DisplayName("Set and get volume")
    void volumeTest() {
        Double volume = 1420693.11d;
        unit.setVolume(volume);
        assertThat(unit.getVolume()).isEqualTo(volume);
    }

    @Test
    @DisplayName("Set and get markets")
    void marketsTest() {
        Market market = mock(Market.class);
        unit.setMarkets(Collections.singletonList(market));
        assertThat(unit.getMarkets()).containsOnly(market);
    }

    @Test
    @DisplayName("Set and get event participants")
    void eventParticipantsTest() {
        EventParticipant eventParticipant = mock(EventParticipant.class);
        unit.setEventParticipants(Collections.singletonList(eventParticipant));
        assertThat(unit.getEventParticipants()).containsOnly(eventParticipant);
    }

    @Test
    @DisplayName("Set and get meta tags")
    void metaTagsTest() {
        MetaTag metaTag = mock(MetaTag.class);
        unit.setMetaTags(Collections.singletonList(metaTag));
        assertThat(unit.getMetaTags()).containsOnly(metaTag);
    }

}
