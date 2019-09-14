package com.matchbook.sdk.rest.dtos.offers.readers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.ResponseReader;
import com.matchbook.sdk.rest.dtos.events.MarketType;
import com.matchbook.sdk.rest.dtos.offers.CommissionType;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;
import com.matchbook.sdk.rest.dtos.offers.Offer;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferStatus;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.prices.Side;

public class OfferReader extends ResponseReader<Offer> {

    private final MatchedBetReader matchedBetReader;
    private final OfferEditReader offerEditReader;

    public OfferReader() {
        super();
        matchedBetReader = new MatchedBetReader();
        offerEditReader = new OfferEditReader();
    }

    @Override
    protected Offer readItem() throws MatchbookSDKParsingException {
        final Offer offer = new Offer();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                offer.setId(parser.getLong());
            } else if ("event-id".equals(fieldName)) {
                offer.setEventId(parser.getLong());
            } else if ("event-name".equals(fieldName)) {
                offer.setEventName(parser.getString());
            } else if ("market-id".equals(fieldName)) {
                offer.setMarketId(parser.getLong());
            } else if ("market-name".equals(fieldName)) {
                offer.setMarketName(parser.getString());
            } else if ("market-type".equals(fieldName)) {
                offer.setMarketType(parser.getEnum(MarketType.class));
            } else if ("runner-id".equals(fieldName)) {
                offer.setRunnerId(parser.getLong());
            } else if ("runner-name".equals(fieldName)) {
                offer.setRunnerName(parser.getString());
            } else if ("exchange-type".equals(fieldName)) {
                offer.setExchangeType(parser.getEnum(ExchangeType.class));
            } else if ("side".equals(fieldName)) {
                offer.setSide(parser.getEnum(Side.class));
            } else if ("odds-type".equals(fieldName)) {
                offer.setOddsType(parser.getEnum(OddsType.class));
            } else if ("odds".equals(fieldName)) {
                offer.setOdds(parser.getDecimal());
            } else if ("decimal-odds".equals(fieldName)) {
                offer.setDecimalOdds(parser.getDecimal());
            } else if ("currency".equals(fieldName)) {
                offer.setCurrency(parser.getEnum(Currency.class));
            } else if ("stake".equals(fieldName)) {
                offer.setStake(parser.getDecimal());
            } else if ("remaining".equals(fieldName)) {
                offer.setRemaining(parser.getDecimal());
            } else if ("potential-liability".equals(fieldName)) {
                offer.setPotentialLiability(parser.getDecimal());
            } else if ("remaining-potential-liability".equals(fieldName)) {
                offer.setRemainingPotentialLiability(parser.getDecimal());
            } else if ("commission-type".equals(fieldName)) {
                offer.setCommissionType(parser.getEnum(CommissionType.class));
            } else if ("originator-commission-rate".equals(fieldName)) {
                offer.setOriginatorCommissionRate(parser.getDecimal());
            } else if ("acceptor-commission-rate".equals(fieldName)) {
                offer.setAcceptorCommissionRate(parser.getDecimal());
            } else if ("commission-reserve".equals(fieldName)) {
                offer.setCommissionReserve(parser.getDecimal());
            } else if ("status".equals(fieldName)) {
                offer.setStatus(parser.getEnum(OfferStatus.class));
            } else if ("created-at".equals(fieldName)) {
                offer.setCreatedAt(parser.getInstant());
            } else if ("in-play".equals(fieldName)) {
                offer.setInPlay(parser.getBoolean());
            } else if ("keep-in-play".equals(fieldName)) {
                offer.setKeepInPlay(parser.getBoolean());
            } else if ("offer-edit".equals(fieldName)) {
                offerEditReader.startReading(parser);
                OfferEdit offerEdit = offerEditReader.readFullResponse();
                offer.setOfferEdit(offerEdit);
            } else if ("matched-bets".equals(fieldName)) {
                List<MatchedBet> matchedBets = readMatchedBets();
                offer.setMatchedBets(matchedBets);
            }
            parser.moveToNextToken();
        }
        return offer;
    }

    private List<MatchedBet> readMatchedBets() {
        List<MatchedBet> matchedBets = new ArrayList<>();
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            matchedBetReader.startReading(parser);
            MatchedBet market = matchedBetReader.readFullResponse();
            if (Objects.nonNull(market)) {
                matchedBets.add(market);
            }
            parser.moveToNextToken();
        }
        return matchedBets;
    }

}
