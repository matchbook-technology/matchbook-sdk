package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.readers.ResponseReader;
import com.matchbook.sdk.rest.dtos.offers.OfferEdit;
import com.matchbook.sdk.rest.dtos.offers.OfferEditStatus;
import com.matchbook.sdk.rest.dtos.prices.OddsType;

public class OfferEditReader extends ResponseReader<OfferEdit> {

    public OfferEditReader() {
        super();
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
                offerEdit.setOddsBefore(parser.getDecimal());
            } else if ("odds-after".equals(fieldName)) {
                offerEdit.setOddsAfter(parser.getDecimal());
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
            }
            parser.moveToNextToken();
        }
        return offerEdit;
    }

}
