package com.matchbook.sdk.rest.dtos.errors;

import com.matchbook.sdk.rest.dtos.RestResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Errors implements RestResponse<Errors> {

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

    @Override
    public Collection<Errors> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return Errors.class.getSimpleName() + " {" +
                ", errors=" + errors +
                "}";
    }

}
