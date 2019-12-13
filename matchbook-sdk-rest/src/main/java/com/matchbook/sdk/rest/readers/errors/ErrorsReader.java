package com.matchbook.sdk.rest.readers.errors;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.readers.ResponseReader;

import java.util.ArrayList;
import java.util.List;

public class ErrorsReader extends ResponseReader<Errors> {

    private final ErrorReader errorReader;

    public ErrorsReader() {
        super();
        errorReader = new ErrorReader();
    }

    @VisibleForTesting
    ErrorsReader(ErrorReader errorReader) {
        this.errorReader = errorReader;
    }

    @Override
    public Errors readItem() throws MatchbookSDKParsingException {
        final Errors errors = new Errors();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("errors".equals(fieldName)) {
                List<Error> errorsList = readErrors();
                errors.setErrors(errorsList);
            }
            parser.moveToNextToken();
        }
        return errors;
    }

    private List<Error> readErrors() {
        List<Error> errors = new ArrayList<>();
        parser.moveToNextToken();
        while (!parser.isEndOfArray()) {
            errorReader.init(parser);
            Error error = errorReader.readFullResponse();
            errors.add(error);
            parser.moveToNextToken();
        }
        return errors;
    }

}
