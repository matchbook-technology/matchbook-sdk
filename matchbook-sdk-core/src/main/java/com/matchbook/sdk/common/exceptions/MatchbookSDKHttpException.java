package com.matchbook.sdk.common.exceptions;

public class MatchbookSDKHttpException extends MatchbookSDKException {

    private static final long serialVersionUID = 110769081415471678L;

    public MatchbookSDKHttpException(String message, Throwable cause) {
        super(message, cause, ErrorType.HTTP);
    }

    public MatchbookSDKHttpException(String message) {
        super(message, ErrorType.HTTP);
    }

    public MatchbookSDKHttpException(Throwable cause) {
        super(cause, ErrorType.HTTP);
    }

    public MatchbookSDKHttpException(String message, ErrorType errorType) {
        super(message, errorType);
    }

}
