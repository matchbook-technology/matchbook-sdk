package com.matchbook.sdk.rest.readers.user;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.readers.ResponseReader;
import com.matchbook.sdk.rest.dtos.user.Balance;

public class BalanceReader extends ResponseReader<Balance> {

    public BalanceReader() {
        super();
    }

    @Override
    protected Balance readItem() throws MatchbookSDKParsingException {
        final Balance balance = new Balance();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                balance.setId(parser.getLong());
            } else if ("balance".equals(fieldName)) {
                balance.setBalance(parser.getDecimal());
            } else if ("exposure".equals(fieldName)) {
                balance.setExposure(parser.getDecimal());
            } else if ("free-funds".equals(fieldName)) {
                balance.setFreeFunds(parser.getDecimal());
            } else if ("commission-reserve".equals(fieldName)) {
                balance.setCommissionReserve(parser.getDecimal());
            }
            parser.moveToNextToken();
        }
        return balance;
    }

}
