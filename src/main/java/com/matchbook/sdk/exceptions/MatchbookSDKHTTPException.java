package com.matchbook.sdk.exceptions;

public class MatchbookSDKHTTPException extends MatchbookSDKException {

    public MatchbookSDKHTTPException(String message, Throwable cause) {
        super(message, cause, ErrorCode.HTTP_ERROR);
    }

    public MatchbookSDKHTTPException(String message) {
        super(message, ErrorCode.HTTP_ERROR);
    }

    public MatchbookSDKHTTPException(Throwable cause) {
        super(cause, ErrorCode.HTTP_ERROR);
    }
}
