package com.matchbook.sdk.rest;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.dtos.RestResponse;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.AbstractBooleanAssert;

abstract class ResponseStreamObserver<T extends RestResponse> implements StreamObserver<T> {

    private final static int COUNT_DOWN_TIMEOUT = 10;

    protected final CountDownLatch countDownLatch;

    protected ResponseStreamObserver(int count) {
        countDownLatch = new CountDownLatch(count);
    }

    public void waitTermination() {
        waitTermination(null);
    }

    protected void waitTermination(String failureMessage) {
        try {
            boolean await = countDownLatch.await(COUNT_DOWN_TIMEOUT, TimeUnit.SECONDS);
            AbstractBooleanAssert<?> booleanAssert = assertThat(await);
            if (Objects.nonNull(failureMessage)) {
                booleanAssert = booleanAssert.withFailMessage(failureMessage);
            }
            booleanAssert.isTrue();
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

}
