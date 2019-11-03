package com.matchbook.sdk.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.FailableRestResponse;
import com.matchbook.sdk.rest.dtos.PartiallyFailableResponse;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.readers.Reader;
import com.matchbook.sdk.rest.readers.errors.ErrorsReader;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class PartiallyFailableResponseCallbackTest
        extends BaseResponseCallbackTest<PartiallyFailableResponseCallback<PartiallyFailableResponseCallbackTest.TestFailableRestResponse,
                PartiallyFailableResponseCallbackTest.TestPartiallyFailableResponse>,
                PartiallyFailableResponseCallbackTest.TestFailableRestResponse,
                PartiallyFailableResponseCallbackTest.TestPartiallyFailableResponse> {

    @Mock
    private StreamObserver<TestFailableRestResponse> streamObserver;

    @Mock
    private Reader<TestFailableRestResponse, TestPartiallyFailableResponse> reader;

    @Override
    protected StreamObserver<TestFailableRestResponse> getStreamObserver() {
        return streamObserver;
    }

    @Override
    protected Reader<TestFailableRestResponse, TestPartiallyFailableResponse> getReader() {
        return reader;
    }

    @Override
    protected PartiallyFailableResponseCallback<TestFailableRestResponse, TestPartiallyFailableResponse>
            newResponseCallback(StreamObserver<TestFailableRestResponse> streamObserver, Serializer serializer,
                    Reader<TestFailableRestResponse, TestPartiallyFailableResponse> reader, ErrorsReader errorsReader) {
        return new PartiallyFailableResponseCallback<>(streamObserver, serializer, reader, errorsReader);
    }

    @Override
    protected TestFailableRestResponse newItem() {
        return mock(TestFailableRestResponse.class);
    }

    @Test
    @DisplayName("Parse partially failed response correctly")
    void onFailableResponsePartiallyFailedTest() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        when(inputStream.read(any(byte[].class))).thenReturn(-1);
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);

        TestFailableRestResponse item = newItem();
        when(reader.readNextItem()).thenReturn(item);
        when(reader.hasMoreItems()).thenReturn(true, false);

        unit.onFailedResponse(inputStream, 400);

        verify(streamObserver).onNext(item);
        verify(streamObserver).onCompleted();
    }

    @Test
    @DisplayName("Parse failed response correctly if not partially failed")
    void onFailableResponseFailedTest() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        when(inputStream.read(any(byte[].class))).thenReturn(1, -1);
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);

        when(reader.hasMoreItems()).thenReturn(false);
        when(errorsReader.readFullResponse()).thenReturn(null);

        unit.onFailedResponse(inputStream, 400);

        verify(streamObserver).onError(any(MatchbookSDKHttpException.class));
    }

    @Test
    @DisplayName("Parse failed response correctly if parsing error in first parse")
    void onFailableResponseParserInitialisationErrorTest() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        when(inputStream.read(any(byte[].class))).thenReturn(-1);
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);

        doThrow(MatchbookSDKParsingException.class).when(reader).init(any(Parser.class));
        when(errorsReader.readFullResponse()).thenReturn(null);

        unit.onFailedResponse(inputStream, 400);

        verify(streamObserver).onError(any(MatchbookSDKHttpException.class));
    }

    @Test
    @DisplayName("I/O error while parsing failable response")
    void onFailableResponseInputErrorTest() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        when(inputStream.read(any(byte[].class))).thenReturn(1, -1);
        doThrow(IOException.class).when(serializer).newParser(any(InputStream.class));

        unit.onFailedResponse(inputStream, 400);

        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
    }

    @Test
    @DisplayName("I/O error while copying input stream on failable response")
    void onFailableResponseInputCopyErrorTest() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        when(inputStream.read(any(byte[].class))).thenThrow(IOException.class);

        unit.onFailedResponse(inputStream, 400);

        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
    }

    static class TestFailableRestResponse implements FailableRestResponse {

        @Override
        public Errors getErrors() {
            return null;
        }
    }

    static class TestPartiallyFailableResponse extends PartiallyFailableResponse<TestFailableRestResponse> {}

}
