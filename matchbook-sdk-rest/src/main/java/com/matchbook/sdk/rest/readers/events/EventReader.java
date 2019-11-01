package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.events.Event;
import com.matchbook.sdk.rest.dtos.events.EventParticipant;
import com.matchbook.sdk.rest.dtos.events.EventStatus;
import com.matchbook.sdk.rest.dtos.events.Market;
import com.matchbook.sdk.rest.dtos.events.MetaTag;
import com.matchbook.sdk.rest.dtos.events.TagType;
import com.matchbook.sdk.rest.readers.ResponseReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventReader extends ResponseReader<Event> {

    private final MarketReader marketReader;

    public EventReader() {
        super();
        marketReader = new MarketReader();
    }

    @Override
    protected Event readItem() throws MatchbookSDKParsingException {
        final Event event = new Event();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                event.setId(parser.getLong());
            } else if ("sport-id".equals(fieldName)) {
                event.setSportId(parser.getLong());
            } else if ("name".equals(fieldName)) {
                event.setName(parser.getString());
            } else if ("start".equals(fieldName)) {
                event.setStart(parser.getInstant());
            } else if ("status".equals(fieldName)) {
                event.setStatus(parser.getEnum(EventStatus.class));
            } else if ("in-running-flag".equals(fieldName)) {
                event.setInRunning(parser.getBoolean());
            } else if ("allow-live-betting".equals(fieldName)) {
                event.setAllowLiveBetting(parser.getBoolean());
            } else if ("volume".equals(fieldName)) {
                event.setVolume(parser.getDouble());
            } else if ("category-id".equals(fieldName)) {
                Long[] categoryIds = readCategoryIds();
                event.setCategoryIds(categoryIds);
            } else if ("markets".equals(fieldName)) {
                List<Market> markets = readMarkets();
                event.setMarkets(markets);
            } else if ("event-participants".equals(fieldName)) {
                List<EventParticipant> eventParticipants = readEventParticipants();
                event.setEventParticipants(eventParticipants);
            } else if ("meta-tags".equals(fieldName)) {
                List<MetaTag> metaTags = readMetaTags();
                event.setMetaTags(metaTags);
            }
            parser.moveToNextToken();
        }
        return event;
    }

    private Long[] readCategoryIds() {
        List<Long> categories = new ArrayList<>();
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            Long categoryId = parser.getLong();
            categories.add(categoryId);
            parser.moveToNextToken();
        }
        return categories.toArray(new Long[0]);
    }

    private List<Market> readMarkets() {
        List<Market> markets = new ArrayList<>();
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            marketReader.init(parser);
            Market market = marketReader.readFullResponse();
            if (Objects.nonNull(market)) {
                markets.add(market);
            }
            parser.moveToNextToken();
        }
        return markets;
    }

    private List<EventParticipant> readEventParticipants() {
        List<EventParticipant> eventParticipants = new ArrayList<>();
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            EventParticipant eventParticipant = readEventParticipant();
            eventParticipants.add(eventParticipant);
            parser.moveToNextToken();
        }
        return eventParticipants;
    }

    private EventParticipant readEventParticipant() {
        final EventParticipant eventParticipant = new EventParticipant();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                eventParticipant.setId(parser.getLong());
            } else if ("event-id".equals(fieldName)) {
                eventParticipant.setEventId(parser.getLong());
            } else if ("participant-name".equals(fieldName)) {
                eventParticipant.setParticipantName(parser.getString());
            } else if ("jockey-name".equals(fieldName)) {
                eventParticipant.setJockeyName(parser.getString());
            } else if ("trainer-name".equals(fieldName)) {
                eventParticipant.setTrainerName(parser.getString());
            } else if ("number".equals(fieldName)) {
                eventParticipant.setNumber(parser.getInteger());
            }
            parser.moveToNextToken();
        }
        return eventParticipant;
    }

    private List<MetaTag> readMetaTags() {
        List<MetaTag> metaTags = new ArrayList<>();
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            MetaTag metaTag = readMetaTag();
            metaTags.add(metaTag);
            parser.moveToNextToken();
        }
        return metaTags;
    }

    private MetaTag readMetaTag() {
        final MetaTag metaTag = new MetaTag();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                metaTag.setId(parser.getLong());
            } else if ("name".equals(fieldName)) {
                metaTag.setName(parser.getString());
            } else if ("url-name".equals(fieldName)) {
                metaTag.setUrlName(parser.getString());
            } else if ("type".equals(fieldName)) {
                metaTag.setType(parser.getEnum(TagType.class));
            }
            parser.moveToNextToken();
        }
        return metaTag;
    }

}
