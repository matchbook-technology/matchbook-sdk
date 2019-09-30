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

public class RestResponseCallback<T, RESP extends RestResponse> implements HttpCallback {

    private final StreamObserver<T> observer;
    private final Serializer serializer;
    private final Reader<T, RESP> reader;
    private final ErrorsReader errorsReader;

    RestResponseCallback(StreamObserver<T> observer, Serializer serializer, Reader<T, RESP> reader) {
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
        } catch (IOException e) {
            MatchbookSDKParsingException exception = new MatchbookSDKParsingException(e.getMessage(), e);
            observer.onError(exception);
        }
    }

    @Override
    public void onFailedResponse(InputStream responseInputStream, int responseCode) {
        try (Parser parser = serializer.newParser(responseInputStream)) {
            errorsReader.init(parser);
            Errors errors = errorsReader.readFullResponse();
            MatchbookSDKHttpException matchbookException = Objects.nonNull(errors) && isAuthenticationErrorPresent(errors) ?
                    unauthenticatedException() : httpException(responseCode);
            observer.onError(matchbookException);
        } catch (IOException e) {
            MatchbookSDKParsingException exception = new MatchbookSDKParsingException(e.getMessage(), e);
            observer.onError(exception);
        }
    }

    private boolean isAuthenticationErrorPresent(Errors errors) {
        return errors.getErrors().stream()
                .flatMap(error -> error.getMessages().stream())
                .anyMatch(message -> message.toLowerCase().contains("cannot login"));
    }

    private MatchbookSDKHttpException unauthenticatedException() {
        return new MatchbookSDKHttpException("Unable to authenticate user: invalid credentials", ErrorType.UNAUTHENTICATED);
    }

    private MatchbookSDKHttpException httpException(int responseCode) {
        return new MatchbookSDKHttpException("Unexpected HTTP code " + responseCode, ErrorType.HTTP);
    }

    @Override
    public void onException(IOException ioException) {
        MatchbookSDKHttpException matchbookException = new MatchbookSDKHttpException(ioException.getMessage(), ioException);
        observer.onError(matchbookException);
    }

}
