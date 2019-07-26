package com.matchbook.sdk.core.exceptions;

public class MatchbookSDKException extends RuntimeException {

    private static final long serialVersionUID = -7090006730979505152L;

    private final ErrorType errorType;

    public MatchbookSDKException(String message, Throwable cause, ErrorType errorType) {
        super(message, cause);
        this.errorType = errorType;
    }

    public MatchbookSDKException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public MatchbookSDKException(Throwable cause, ErrorType errorType) {
        super(cause);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

}
