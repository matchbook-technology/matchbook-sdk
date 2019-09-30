package com.matchbook.sdk.rest;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.FailableRestResponse;
import com.matchbook.sdk.rest.dtos.PartiallyFailableResponse;
import com.matchbook.sdk.rest.readers.Reader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

class PartiallyFailableResponseCallback<T extends FailableRestResponse, RESP extends PartiallyFailableResponse>
        extends RestResponseCallback<T, RESP> {

    private static final int FAILABLE_HTTP_CODE = 400;

    PartiallyFailableResponseCallback(StreamObserver<T> observer, Serializer serializer, Reader<T, RESP> reader) {
        super(observer, serializer, reader);
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
                InputStream copiedResponseInputStream = copyStream(responseBytes)) {
            try (Parser parser = serializer.newParser(copiedResponseInputStream)) {
                boolean isPartiallyFailedResponse = false;
                reader.init(parser);
                while (reader.hasMoreItems()) {
                    T item = reader.readNextItem();
                    isPartiallyFailedResponse |= Objects.nonNull(item);
                    observer.onNext(item);
                }
                if (isPartiallyFailedResponse) {
                    observer.onCompleted();
                } else {
                    try (InputStream otherResponseInputStream = copyStream(responseBytes)) {
                        super.onFailedResponse(otherResponseInputStream, responseCode);
                    }
                }
            } catch (MatchbookSDKParsingException parsingException) {
                try (InputStream otherResponseInputStream = copyStream(responseBytes)) {
                    super.onFailedResponse(otherResponseInputStream, responseCode);
                }
            }
        } catch (IOException ioException) {
            MatchbookSDKParsingException exception = parsingException(ioException);
            observer.onError(exception);
        }
    }

    private ByteArrayOutputStream copyBytes(InputStream responseInputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytesBuffer = new byte[1024];
        int length;
        while ((length = responseInputStream.read(bytesBuffer)) > -1 ) {
            outputStream.write(bytesBuffer, 0, length);
        }
        outputStream.flush();
        return  outputStream;
    }

    private InputStream copyStream(ByteArrayOutputStream byteArrayOutputStream) {
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

}
