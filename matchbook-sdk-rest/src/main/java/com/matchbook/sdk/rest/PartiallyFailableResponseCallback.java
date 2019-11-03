package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.FailableRestResponse;
import com.matchbook.sdk.rest.dtos.PartiallyFailableResponse;
import com.matchbook.sdk.rest.readers.Reader;
import com.matchbook.sdk.rest.readers.errors.ErrorsReader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

class PartiallyFailableResponseCallback<T extends FailableRestResponse, RESP extends PartiallyFailableResponse<T>>
        extends RestResponseCallback<T, RESP> {

    private static final int FAILABLE_HTTP_CODE = 400;

    PartiallyFailableResponseCallback(StreamObserver<T> observer, Serializer serializer, Reader<T, RESP> reader) {
        super(observer, serializer, reader);
    }

    @VisibleForTesting
    PartiallyFailableResponseCallback(StreamObserver<T> observer, Serializer serializer,
            Reader<T, RESP> reader, ErrorsReader errorsReader) {
        super(observer, serializer, reader, errorsReader);
    }

    @Override
    public void onFailedResponse(InputStream responseInputStream, int responseCode) {
        if (responseCode == FAILABLE_HTTP_CODE) {
            onFailableResponse(responseInputStream, responseCode);
        } else {
            super.onFailedResponse(responseInputStream, responseCode);
        }
    }

    private void onFailableResponse(InputStream responseInputStream, int responseCode) {
        try (ByteArrayOutputStream responseBytes = copyBytes(responseInputStream);
                InputStream copiedInputStream = copyInputStream(responseBytes)) {
            try (Parser parser = serializer.newParser(copiedInputStream)) {
                boolean isPartiallyFailedResponse = tryPartiallyFailedResponse(parser);
                if (isPartiallyFailedResponse) {
                    streamObserver.onCompleted();
                } else {
                    secondParsing(responseBytes, responseCode);
                }
            } catch (MatchbookSDKParsingException parsingException) {
                secondParsing(responseBytes, responseCode);
            }
        } catch (IOException ioException) {
            MatchbookSDKParsingException exception = parsingException(ioException);
            streamObserver.onError(exception);
        }
    }

    private ByteArrayOutputStream copyBytes(InputStream responseInputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytesBuffer = new byte[1024];
        int length;
        while ((length = responseInputStream.read(bytesBuffer)) > -1) {
            outputStream.write(bytesBuffer, 0, length);
        }
        outputStream.flush();
        return  outputStream;
    }

    private boolean tryPartiallyFailedResponse(Parser parser) {
        boolean isPartiallyFailedResponse = false;
        reader.init(parser);
        while (reader.hasMoreItems()) {
            T item = reader.readNextItem();
            isPartiallyFailedResponse |= Objects.nonNull(item);
            streamObserver.onNext(item);
        }
        return isPartiallyFailedResponse;
    }

    private void secondParsing(ByteArrayOutputStream responseBytes, int responseCode) {
        try (InputStream otherResponseInputStream = copyInputStream(responseBytes)) {
            super.onFailedResponse(otherResponseInputStream, responseCode);
        } catch (IOException ioException) {
            MatchbookSDKParsingException exception = parsingException(ioException);
            streamObserver.onError(exception);
        }
    }

    private InputStream copyInputStream(ByteArrayOutputStream byteArrayOutputStream) {
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

}
