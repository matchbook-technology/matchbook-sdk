package com.matchbook.sdk.rest;

import static org.assertj.core.api.Assertions.fail;

import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.dtos.RestResponse;

import java.util.Objects;
import java.util.function.Consumer;

class FailedResponseStreamObserver<T extends RestResponse> extends ResponseStreamObserver<T> {

    private final Consumer<MatchbookSDKException> exceptionVerifier;

    FailedResponseStreamObserver() {
        this(null);
    }

    FailedResponseStreamObserver(Consumer<MatchbookSDKException> exceptionVerifier) {
        super(1);
        this.exceptionVerifier = exceptionVerifier;
    }

    @Override
    public void onNext(T item) {
        fail("Unexpected item received from server.");
    }

    @Override
    public void onCompleted() {
        fail("Unexpected successful completion of request.");
    }

    @Override
    public <E extends MatchbookSDKException> void onError(E exception) {
        if (Objects.nonNull(exceptionVerifier)) {
            exceptionVerifier.accept(exception);
        }
        countDownLatch.countDown();
    }

}
