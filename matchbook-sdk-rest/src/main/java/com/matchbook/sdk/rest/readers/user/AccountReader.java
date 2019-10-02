package com.matchbook.sdk.rest.readers.user;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.readers.ResponseReader;
import com.matchbook.sdk.rest.dtos.offers.CommissionType;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.ExchangeType;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.dtos.user.Account;

public class AccountReader extends ResponseReader<Account> {

    public AccountReader() {
        super();
    }

    @Override
    protected Account readItem() throws MatchbookSDKParsingException {
        final Account account = new Account();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                account.setId(parser.getLong());
            } else if ("username".equals(fieldName)) {
                account.setUsername(parser.getString());
            } else if ("exchange-type".equals(fieldName)) {
                account.setExchangeType(parser.getEnum(ExchangeType.class));
            } else if ("odds-type".equals(fieldName)) {
                account.setOddsType(parser.getEnum(OddsType.class));
            } else if ("currency".equals(fieldName)) {
                account.setCurrency(parser.getEnum(Currency.class));
            } else if ("balance".equals(fieldName)) {
                account.setBalance(parser.getDecimal());
            } else if ("exposure".equals(fieldName)) {
                account.setExposure(parser.getDecimal());
            } else if ("free-funds".equals(fieldName)) {
                account.setFreeFunds(parser.getDecimal());
            } else if ("commission-reserve".equals(fieldName)) {
                account.setCommissionReserve(parser.getDecimal());
            } else if ("commission-type".equals(fieldName)) {
                account.setCommissionType(parser.getEnum(CommissionType.class));
            } else if ("name".equals(fieldName) ||
                    "address".equals(fieldName) ||
                    "roles".equals(fieldName) ||
                    "user-security-question".equals(fieldName)) {
                parser.skipChildren();
            }
            parser.moveToNextToken();
        }
        return account;
    }

}
