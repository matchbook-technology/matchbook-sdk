package com.matchbook.sdk.rest;

import static org.mockito.Mockito.mock;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.RestResponse;
import com.matchbook.sdk.rest.readers.Reader;
import com.matchbook.sdk.rest.readers.errors.ErrorsReader;

import org.mockito.Mock;

class ResponseCallbackTest
        extends BaseResponseCallbackTest<ResponseCallback<Object, ResponseCallbackTest.TestRestResponse>,
                Object, ResponseCallbackTest.TestRestResponse> {

    @Mock
    private StreamObserver<Object> streamObserver;

    @Mock
    private Reader<Object, TestRestResponse> reader;

    @Override
    protected StreamObserver<Object> getStreamObserver() {
        return streamObserver;
    }

    @Override
    protected Reader<Object, TestRestResponse> getReader() {
        return reader;
    }

    @Override
    protected ResponseCallback<Object, TestRestResponse> newResponseCallback(StreamObserver<Object> streamObserver,
            Serializer serializer, Reader<Object, TestRestResponse> reader, ErrorsReader errorsReader) {
        return new ResponseCallback<>(streamObserver, serializer, reader, errorsReader);
    }

    @Override
    protected Object newItem() {
        return mock(Object.class);
    }

    static class TestRestResponse implements RestResponse {}

}
