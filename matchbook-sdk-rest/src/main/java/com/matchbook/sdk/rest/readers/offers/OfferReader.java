package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.dtos.errors.Errors;
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
import com.matchbook.sdk.rest.readers.ResponseReader;
import com.matchbook.sdk.rest.readers.errors.ErrorReader;

import java.util.ArrayList;
import java.util.List;

public class OfferReader extends ResponseReader<Offer> {

    private final MatchedBetReader matchedBetReader;
    private final OfferEditReader offerEditReader;
    private final ErrorReader errorReader;

    public OfferReader() {
        super();
        matchedBetReader = new MatchedBetReader();
        offerEditReader = new OfferEditReader();
        errorReader = new ErrorReader();
    }

    @VisibleForTesting
    OfferReader(MatchedBetReader matchedBetReader, OfferEditReader offerEditReader, ErrorReader errorReader) {
        this.matchedBetReader = matchedBetReader;
        this.offerEditReader = offerEditReader;
        this.errorReader = errorReader;
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
                offer.setOdds(parser.getDouble());
            } else if ("decimal-odds".equals(fieldName)) {
                offer.setDecimalOdds(parser.getDouble());
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
                offer.setOriginatorCommissionRate(parser.getDouble());
            } else if ("acceptor-commission-rate".equals(fieldName)) {
                offer.setAcceptorCommissionRate(parser.getDouble());
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
                OfferEdit offerEdit = readOfferEdit();
                offer.setOfferEdit(offerEdit);
            } else if ("matched-bets".equals(fieldName)) {
                List<MatchedBet> matchedBets = readMatchedBets();
                offer.setMatchedBets(matchedBets);
            } else if ("errors".equals(fieldName)) {
                Errors errors = readErrors();
                offer.setErrors(errors);
            }
            parser.moveToNextToken();
        }
        return offer;
    }

    private OfferEdit readOfferEdit() {
        offerEditReader.init(parser);
        return offerEditReader.readFullResponse();
    }

    private List<MatchedBet> readMatchedBets() {
        List<MatchedBet> matchedBets = new ArrayList<>();
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            matchedBetReader.init(parser);
            MatchedBet market = matchedBetReader.readFullResponse();
            matchedBets.add(market);
            parser.moveToNextToken();
        }
        return matchedBets;
    }

    private Errors readErrors() {
        List<Error> errorsList = readErrorsList();
        if (!errorsList.isEmpty()) {
             final Errors errors = new Errors();
             errors.setErrors(errorsList);
             return errors;
        }
        return null;
    }

    private List<Error> readErrorsList() {
        List<Error> errorsList = new ArrayList<>(1);
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            errorReader.init(parser);
            Error error = errorReader.readFullResponse();
            errorsList.add(error);
            parser.moveToNextToken();
        }
        return errorsList;
    }

}
