package com.matchbook.sdk.rest.readers.user;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.readers.ResponseReader;
import com.matchbook.sdk.rest.dtos.user.Logout;

public class LogoutReader extends ResponseReader<Logout> {

    public LogoutReader() {
        super();
    }

    @Override
    protected Logout readItem() throws MatchbookSDKParsingException {
        final Logout logout = new Logout();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("session-token".equals(fieldName)) {
                logout.setSessionToken(parser.getString());
            } else if ("user-id".equals(fieldName)) {
                logout.setUserId(parser.getLong());
            } else if ("username".equals(fieldName)) {
                logout.setUsername(parser.getString());
            }
            parser.moveToNextToken();
        }
        return logout;
    }

}
