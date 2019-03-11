package com.matchbook.sdk.core.exceptions;

public class MatchbookSDKHTTPException extends MatchbookSDKException {

    private static final long serialVersionUID = 110769081415471678L;

    public MatchbookSDKHTTPException(String message, Throwable cause) {
        super(message, cause, ErrorCode.HTTP_ERROR);
    }

    public MatchbookSDKHTTPException(String message) {
        super(message, ErrorCode.HTTP_ERROR);
    }

    public MatchbookSDKHTTPException(Throwable cause) {
        super(cause, ErrorCode.HTTP_ERROR);
    }

    public MatchbookSDKHTTPException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
