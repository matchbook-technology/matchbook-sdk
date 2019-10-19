package com.matchbook.sdk.rest.dtos;

import java.util.Objects;

import com.matchbook.sdk.rest.dtos.errors.Errors;

public interface FailableRestResponse extends RestResponse {

    Errors getErrors();

    default boolean hasFailed() {
        Errors errors = getErrors();
        return Objects.nonNull(errors) && !errors.getErrors().isEmpty();
    }

}
