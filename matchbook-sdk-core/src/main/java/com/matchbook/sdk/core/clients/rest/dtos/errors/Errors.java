package com.matchbook.sdk.core.clients.rest.dtos.errors;

import java.util.ArrayList;
import java.util.List;

public class Errors {

    private List<Error> errors = new ArrayList<>();

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
