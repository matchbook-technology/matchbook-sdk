package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.HttpCallback;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.RestResponse;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.readers.Reader;
import com.matchbook.sdk.rest.readers.errors.ErrorsReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

class RestResponseCallback<T, RESP extends RestResponse> implements HttpCallback {

    protected final StreamObserver<T> observer;
    protected final Serializer serializer;
    protected final Reader<T, RESP> reader;

    private final ErrorsReader errorsReader;

    protected RestResponseCallback(StreamObserver<T> observer, Serializer serializer, Reader<T, RESP> reader) {
        this.observer = observer;
        this.serializer = serializer;
        this.reader = reader;

        errorsReader = new ErrorsReader();
    }

    @Override
    public void onSuccessfulResponse(InputStream responseInputStream) {
        try (Parser parser = serializer.newParser(responseInputStream)) {
            reader.init(parser);
            while (reader.hasMoreItems()) {
                T item = reader.readNextItem();
                observer.onNext(item);
            }
            observer.onCompleted();
        } catch (MatchbookSDKParsingException parsingException) {
            observer.onError(parsingException);
        } catch (IOException ioException) {
            MatchbookSDKParsingException exception = parsingException(ioException);
            observer.onError(exception);
        }
    }

    @Override
    public void onFailedResponse(InputStream responseInputStream, int responseCode) {
        try (Parser parser = serializer.newParser(responseInputStream)) {
            errorsReader.init(parser);
            Errors errors = errorsReader.readFullResponse();

            MatchbookSDKHttpException matchbookException = Objects.nonNull(errors) && isAuthenticationError(errors) ?
                    unauthenticatedException() : httpException(responseCode);
            observer.onError(matchbookException);
        } catch (MatchbookSDKParsingException parsingException) {
            observer.onError(parsingException);
        } catch (IOException ioException) {
            MatchbookSDKParsingException exception = parsingException(ioException);
            observer.onError(exception);
        }
    }

    private boolean isAuthenticationError(Errors errors) {
        return errors.getErrors().stream()
                .flatMap(error -> error.getMessages().stream())
                .anyMatch(message -> message.toLowerCase().contains("cannot login"));
    }

    @Override
    public void onException(IOException ioException) {
        MatchbookSDKHttpException matchbookException = httpException(ioException);
        observer.onError(matchbookException);
    }

    protected MatchbookSDKParsingException parsingException(IOException ioException) {
        return new MatchbookSDKParsingException(ioException.getMessage(), ioException);
    }

    private MatchbookSDKHttpException unauthenticatedException() {
        return new MatchbookSDKHttpException("Unable to authenticate user. Invalid credentials.", ErrorType.UNAUTHENTICATED);
    }

    private MatchbookSDKHttpException httpException(int responseCode) {
        return new MatchbookSDKHttpException(String.format("Unexpected HTTP response code: %d.", responseCode), ErrorType.HTTP);
    }

    private MatchbookSDKHttpException httpException(IOException ioException) {
        return new MatchbookSDKHttpException(ioException.getMessage(), ioException);
    }

}
