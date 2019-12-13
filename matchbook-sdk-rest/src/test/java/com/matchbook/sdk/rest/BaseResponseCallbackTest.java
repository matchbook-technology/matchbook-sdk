package com.matchbook.sdk.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.RestResponse;
import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.readers.Reader;
import com.matchbook.sdk.rest.readers.errors.ErrorsReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
abstract class BaseResponseCallbackTest<X extends ResponseCallback<T, RESP>, T, RESP extends RestResponse> {

    @Mock
    protected Serializer serializer;

    @Mock
    protected ErrorsReader errorsReader;

    private StreamObserver<T> streamObserver;
    private Reader<T, RESP> reader;

    protected X unit;

    protected abstract StreamObserver<T> getStreamObserver();

    protected abstract Reader<T, RESP> getReader();

    protected abstract X newResponseCallback(StreamObserver<T> streamObserver, Serializer serializer,
            Reader<T, RESP> reader, ErrorsReader errorsReader);

    protected abstract T newItem();

    @BeforeEach
    void setUp() {
        streamObserver = getStreamObserver();
        reader = getReader();
        unit = newResponseCallback(streamObserver, serializer, reader, errorsReader);
    }

    @Test
    @DisplayName("Parse successfully response correctly")
    void onSuccessfulResponseTest() throws IOException {
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);

        T item1 = newItem();
        T item2 = newItem();
        when(reader.readNextItem()).thenReturn(item1).thenReturn(item2);
        when(reader.hasMoreItems()).thenReturn(true, true, false);

        InputStream inputStream = mock(InputStream.class);
        unit.onSuccessfulResponse(inputStream);

        verify(streamObserver).onNext(item1);
        verify(streamObserver).onNext(item2);
        verify(streamObserver).onCompleted();
    }

    @Test
    @DisplayName("Parsing error while parsing successfully response")
    void onSuccessfulResponseReadErrorTest() throws IOException {
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);

        T item = newItem();
        when(reader.readNextItem()).thenReturn(item).thenThrow(MatchbookSDKParsingException.class);
        when(reader.hasMoreItems()).thenReturn(true);

        InputStream inputStream = mock(InputStream.class);
        unit.onSuccessfulResponse(inputStream);

        verify(streamObserver).onNext(item);
        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
    }

    @Test
    @DisplayName("Parser initialisation error on successfully response")
    void onSuccessfulResponseParserInitialisationErrorTest() throws IOException {
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);
        doThrow(MatchbookSDKParsingException.class).when(reader).init(any(Parser.class));

        InputStream inputStream = mock(InputStream.class);
        unit.onSuccessfulResponse(inputStream);

        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
    }

    @Test
    @DisplayName("I/O error while parsing successfully response")
    void onSuccessfulResponseInputErrorTest() throws IOException {
        doThrow(IOException.class).when(serializer).newParser(any(InputStream.class));

        InputStream inputStream = mock(InputStream.class);
        unit.onSuccessfulResponse(inputStream);

        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
    }

    @Test
    @DisplayName("No errors included in failed response")
    void onFailedResponseNoErrorsTest() throws IOException {
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);

        when(errorsReader.readFullResponse()).thenReturn(null);

        InputStream inputStream = mock(InputStream.class);
        unit.onFailedResponse(inputStream, 500);

        ArgumentCaptor<MatchbookSDKException> exceptionCaptor = ArgumentCaptor.forClass(MatchbookSDKException.class);
        verify(streamObserver).onError(exceptionCaptor.capture());
        MatchbookSDKException exception = exceptionCaptor.getValue();
        assertThat(exception).isInstanceOf(MatchbookSDKHttpException.class);
        assertThat(exception.getErrorType()).isEqualTo(ErrorType.HTTP);
    }

    @Test
    @DisplayName("No authentication errors included in failed response")
    void onFailedResponseNoAuthenticationErrorsTest() throws IOException {
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);

        Error error = mock(Error.class);
        when(error.getMessages()).thenReturn(Collections.singletonList("Impossible to find element with ID 42."));
        Errors errors = mock(Errors.class);
        when(errors.getErrors()).thenReturn(Collections.singletonList(error));
        when(errorsReader.readFullResponse()).thenReturn(errors);

        InputStream inputStream = mock(InputStream.class);
        unit.onFailedResponse(inputStream, 404);

        ArgumentCaptor<MatchbookSDKException> exceptionCaptor = ArgumentCaptor.forClass(MatchbookSDKException.class);
        verify(streamObserver).onError(exceptionCaptor.capture());
        MatchbookSDKException exception = exceptionCaptor.getValue();
        assertThat(exception).isInstanceOf(MatchbookSDKHttpException.class);
        assertThat(exception.getErrorType()).isEqualTo(ErrorType.HTTP);
    }

    @Test
    @DisplayName("Login error included in failed response")
    void onFailedResponseLoginErrorTest() throws IOException {
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);

        Error error = mock(Error.class);
        when(error.getMessages()).thenReturn(Collections.singletonList("Cannot login. Invalid credentials."));
        Errors errors = mock(Errors.class);
        when(errors.getErrors()).thenReturn(Collections.singletonList(error));
        when(errorsReader.readFullResponse()).thenReturn(errors);

        InputStream inputStream = mock(InputStream.class);
        unit.onFailedResponse(inputStream, 401);

        ArgumentCaptor<MatchbookSDKException> exceptionCaptor = ArgumentCaptor.forClass(MatchbookSDKException.class);
        verify(streamObserver).onError(exceptionCaptor.capture());
        MatchbookSDKException exception = exceptionCaptor.getValue();
        assertThat(exception).isInstanceOf(MatchbookSDKHttpException.class);
        assertThat(exception.getErrorType()).isEqualTo(ErrorType.UNAUTHENTICATED);
    }

    @Test
    @DisplayName("Unauthorized error included in failed response")
    void onFailedResponseUnauthorizedErrorTest() throws IOException {
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);

        Error error = mock(Error.class);
        when(error.getMessages()).thenReturn(Collections.singletonList("Authentication required to get this data."));
        Errors errors = mock(Errors.class);
        when(errors.getErrors()).thenReturn(Collections.singletonList(error));
        when(errorsReader.readFullResponse()).thenReturn(errors);

        InputStream inputStream = mock(InputStream.class);
        unit.onFailedResponse(inputStream, 403);

        ArgumentCaptor<MatchbookSDKException> exceptionCaptor = ArgumentCaptor.forClass(MatchbookSDKException.class);
        verify(streamObserver).onError(exceptionCaptor.capture());
        MatchbookSDKException exception = exceptionCaptor.getValue();
        assertThat(exception).isInstanceOf(MatchbookSDKHttpException.class);
        assertThat(exception.getErrorType()).isEqualTo(ErrorType.UNAUTHENTICATED);
    }

    @Test
    @DisplayName("Parser initialisation error on failed response")
    void onFailedResponseParserInitialisationErrorTest() throws IOException {
        Parser parser = mock(Parser.class);
        when(serializer.newParser(any(InputStream.class))).thenReturn(parser);
        doThrow(MatchbookSDKParsingException.class).when(errorsReader).init(any(Parser.class));

        InputStream inputStream = mock(InputStream.class);
        unit.onFailedResponse(inputStream, 500);

        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
    }

    @Test
    @DisplayName("I/O error while parsing failed response")
    void onFailedResponseInputErrorTest() throws IOException {
        doThrow(IOException.class).when(serializer).newParser(any(InputStream.class));

        InputStream inputStream = mock(InputStream.class);
        unit.onFailedResponse(inputStream, 500);

        verify(streamObserver).onError(any(MatchbookSDKParsingException.class));
    }

    @Test
    @DisplayName("Exception in HTTP request")
    void onExceptionTest() {
        IOException ioException = mock(IOException.class);
        unit.onException(ioException);

        verify(streamObserver).onError(any(MatchbookSDKHttpException.class));
    }

}
