package com.matchbook.sdk.core.exceptions;

public class MatchbookSDKException extends RuntimeException {

    private static final long serialVersionUID = -7090006730979505152L;

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
