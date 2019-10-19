package com.matchbook.sdk.rest;

import static org.assertj.core.api.Assertions.fail;

import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.rest.dtos.RestResponse;

import java.util.Objects;
import java.util.function.Consumer;

class SuccessfulResponseStreamObserver<T extends RestResponse> extends ResponseStreamObserver<T> {

    private final int expectedItems;
    private final Consumer<T> itemVerifier;

    SuccessfulResponseStreamObserver(int expectedItems) {
        this(expectedItems, null);
    }

    SuccessfulResponseStreamObserver(int expectedItems, Consumer<T> itemVerifier) {
        super(expectedItems + 1);
        this.expectedItems = expectedItems;
        this.itemVerifier = itemVerifier;
    }

    @Override
    public void onNext(T item) {
        if (Objects.nonNull(itemVerifier)) {
            itemVerifier.accept(item);
        }
        if (countDownLatch.getCount() == 1) {
            fail(String.format("Successful request not complete. Are you sure the response contains only %d item/s?", expectedItems));
        }
        countDownLatch.countDown();
    }

    @Override
    public void onCompleted() {
        countDownLatch.countDown();
    }

    @Override
    public <E extends MatchbookSDKException> void onError(E exception) {
        fail("Unexpected error from server.", exception);
    }

    @Override
    public void waitTermination() {
        waitTermination(String.format("Are you sure the response contains %d items?", expectedItems));
    }

}
