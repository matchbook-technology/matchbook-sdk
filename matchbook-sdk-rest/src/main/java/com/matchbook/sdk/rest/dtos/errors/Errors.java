package com.matchbook.sdk.rest.dtos.errors;

import java.util.ArrayList;
import java.util.List;

public class Errors {

    private List<Error> errors;

    public Errors() {
        errors = new ArrayList<>(0);
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

}
