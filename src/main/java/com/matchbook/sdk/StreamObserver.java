package com.matchbook.sdk;

import com.matchbook.sdk.exceptions.MatchbookSDKException;

public interface StreamObserver<V> {

    void onNext(V v);

    void onCompleted();

    <E extends MatchbookSDKException> void onError(E exception);

}
