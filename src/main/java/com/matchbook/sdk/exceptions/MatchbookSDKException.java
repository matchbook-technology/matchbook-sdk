package com.matchbook.sdk.exceptions;

public class MatchbookSDKException extends RuntimeException {

    private final ErrorCode errorCode;

    public MatchbookSDKException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public MatchbookSDKException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public MatchbookSDKException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
