package com.matchbook.sdk;

import com.matchbook.sdk.exceptions.MatchbookSDKException;

public interface StreamObserver<V> {
    void onNext(V v);

    void onError(MatchbookSDKException e);

    void onCompleted();
}
