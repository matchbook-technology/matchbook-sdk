package com.matchbook.sdk.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.dtos.errors.Errors;

public abstract class PartiallyFailableResponse<T extends FailableRestResponse>
        extends PageableResponse<T> implements FailableRestResponse {

    protected PartiallyFailableResponse() {
        super();
    }

    @Override
    public boolean hasFailed() {
        return items.stream().anyMatch(FailableRestResponse::hasFailed);
    }

    @Override
    public Errors getErrors() {
        List<Error> errorsList = items.stream()
                .filter(FailableRestResponse::hasFailed)
                .flatMap(item -> item.getErrors().getErrors().stream())
                .collect(Collectors.toList());
        if (!errorsList.isEmpty()) {
            final Errors errors = new Errors();
            errors.setErrors(errorsList);
            return errors;
        }
        return null;
    }

}
