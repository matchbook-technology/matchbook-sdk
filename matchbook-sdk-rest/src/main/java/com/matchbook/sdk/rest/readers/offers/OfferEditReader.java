package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditStatus;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.readers.ResponseReader;
import com.matchbook.sdk.rest.readers.errors.ErrorReader;

import java.util.ArrayList;
import java.util.List;

public class OfferEditReader extends ResponseReader<OfferEdit> {

    private final ErrorReader errorReader;

    public OfferEditReader() {
        super();
        errorReader = new ErrorReader();
    }

    @VisibleForTesting
    OfferEditReader(ErrorReader errorReader) {
        this.errorReader = errorReader;
    }

    @Override
    protected OfferEdit readItem() throws MatchbookSDKParsingException {
        final OfferEdit offerEdit = new OfferEdit();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                offerEdit.setId(parser.getLong());
            } else if ("offer-id".equals(fieldName)) {
                offerEdit.setOfferId(parser.getLong());
            } else if ("runner-id".equals(fieldName)) {
                offerEdit.setRunnerId(parser.getLong());
            } else if ("odds-type".equals(fieldName)) {
                offerEdit.setOddsType(parser.getEnum(OddsType.class));
            } else if ("odds-before".equals(fieldName)) {
                offerEdit.setOddsBefore(parser.getDouble());
            } else if ("odds-after".equals(fieldName)) {
                offerEdit.setOddsAfter(parser.getDouble());
            } else if ("stake-before".equals(fieldName)) {
                offerEdit.setStakeBefore(parser.getDecimal());
            } else if ("stake-after".equals(fieldName)) {
                offerEdit.setStakeAfter(parser.getDecimal());
            } else if ("status".equals(fieldName)) {
                offerEdit.setStatus(parser.getEnum(OfferEditStatus.class));
            } else if ("delay".equals(fieldName)) {
                offerEdit.setDelay(parser.getDouble());
            } else if ("edit-time".equals(fieldName)) {
                offerEdit.setEditTime(parser.getInstant());
            } else if ("errors".equals(fieldName)) {
                Errors errors = readErrors();
                offerEdit.setErrors(errors);
            }
            parser.moveToNextToken();
        }
        return offerEdit;
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
