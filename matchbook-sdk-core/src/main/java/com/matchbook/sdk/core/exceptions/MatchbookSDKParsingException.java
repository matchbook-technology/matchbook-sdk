package com.matchbook.sdk.core.exceptions;

public class MatchbookSDKParsingException extends MatchbookSDKException {

    private static final long serialVersionUID = 4808543873575620833L;

    public MatchbookSDKParsingException(String message, Throwable cause) {
        super(message, cause, ErrorType.PARSING);
    }

    public MatchbookSDKParsingException(String message) {
        super(message, ErrorType.PARSING);
    }

    public MatchbookSDKParsingException(Throwable cause) {
        super(cause, ErrorType.PARSING);
    }

}
