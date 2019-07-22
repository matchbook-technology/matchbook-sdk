package com.matchbook.sdk.common;


import com.matchbook.sdk.common.exceptions.MatchbookSDKException;

public interface StreamObserver<V> {

    void onNext(V v);

    void onCompleted();

    <E extends MatchbookSDKException> void onError(E exception);

}
