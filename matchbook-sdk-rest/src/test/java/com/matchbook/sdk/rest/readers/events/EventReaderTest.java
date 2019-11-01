package com.matchbook.sdk.rest.readers.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.events.Event;
import com.matchbook.sdk.rest.dtos.events.EventParticipant;
import com.matchbook.sdk.rest.dtos.events.EventStatus;
import com.matchbook.sdk.rest.dtos.events.Market;
import com.matchbook.sdk.rest.dtos.events.MetaTag;
import com.matchbook.sdk.rest.dtos.events.TagType;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class EventReaderTest extends ResponseReaderTest<EventReader> {

    @Mock
    private MarketReader marketReader;

    @Override
    protected EventReader newReader() {
        return new EventReader(marketReader);
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, true,
                false, false, false, false, false, true, true);
        when(parser.getFieldName()).thenReturn("id", "sport-id", "name", "start", "status",
                "in-running-flag", "allow-live-betting", "volume", "category-id", "markets",
                "event-participants", "id", "event-id", "participant-name", "jockey-name", "trainer-name", "number",
                "meta-tags", "id", "name", "url-name", "type");
        when(parser.getLong()).thenReturn(1265718779850018L, 24735152712200L, 321315741540022L,
                1265718780370218L, 1265718779850018L, 24735152712200L);
        when(parser.getString()).thenReturn("20:52 Santa Anita", "Our Country", "John Velazquez", "George Weaver",
                "Horse Racing", "horse-racing");
        when(parser.getEnum(EventStatus.class)).thenReturn(EventStatus.PAID);
        when(parser.getEnum(TagType.class)).thenReturn(TagType.SPORT);
        when(parser.getBoolean()).thenReturn(false, true);
        when(parser.getDouble()).thenReturn(83174.18d);
        when(parser.getInteger()).thenReturn(1);
        Instant startTime = Instant.now();
        when(parser.getInstant()).thenReturn(startTime);

        when(parser.isEndOfArray()).thenReturn(false, true, false, false, true, false, true, false, true);
        Market market = mock(Market.class);
        when(marketReader.readFullResponse()).thenReturn(market);

        Event event = unit.readNextItem();

        assertThat(event).isNotNull();
        assertThat(event.getId()).isEqualTo(1265718779850018L);
        assertThat(event.getSportId()).isEqualTo(24735152712200L);
        assertThat(event.getName()).isEqualTo("20:52 Santa Anita");
        assertThat(event.getStart()).isCloseTo(startTime, within(1L, ChronoUnit.SECONDS));
        assertThat(event.getStatus()).isEqualTo(EventStatus.PAID);
        assertThat(event.isInRunning()).isFalse();
        assertThat(event.isAllowLiveBetting()).isTrue();
        assertThat(event.getVolume()).isEqualTo(83174.18d);
        assertThat(event.getCategoryIds()).containsOnly(321315741540022L);
        assertThat(event.getMarkets()).containsOnly(market);

        assertThat(event.getEventParticipants()).hasSize(1);
        EventParticipant eventParticipant = event.getEventParticipants().get(0);
        assertThat(eventParticipant).isNotNull();
        assertThat(eventParticipant.getId()).isEqualTo(1265718780370218L);
        assertThat(eventParticipant.getEventId()).isEqualTo(1265718779850018L);
        assertThat(eventParticipant.getParticipantName()).isEqualTo("Our Country");
        assertThat(eventParticipant.getJockeyName()).isEqualTo("John Velazquez");
        assertThat(eventParticipant.getTrainerName()).isEqualTo("George Weaver");
        assertThat(eventParticipant.getNumber()).isEqualTo(1);

        assertThat(event.getMetaTags()).hasSize(1);
        MetaTag metaTag = event.getMetaTags().get(0);
        assertThat(metaTag).isNotNull();
        assertThat(metaTag.getId()).isEqualTo(24735152712200L);
        assertThat(metaTag.getName()).isEqualTo("Horse Racing");
        assertThat(metaTag.getUrlName()).isEqualTo("horse-racing");
        assertThat(metaTag.getType()).isEqualTo(TagType.SPORT);

        assertThat(unit.hasMoreItems()).isFalse();
    }

}
