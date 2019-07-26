package com.matchbook.sdk.core;


import com.matchbook.sdk.core.exceptions.MatchbookSDKException;

public interface StreamObserver<V> {

    void onNext(V v);

    void onCompleted();

    <E extends MatchbookSDKException> void onError(E exception);

}
