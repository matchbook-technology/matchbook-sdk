package com.matchbook.sdk.rest.dtos.errors;

import com.matchbook.sdk.rest.dtos.RestResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Error implements RestResponse<Error> {

    private String field;
    private List<String> messages;

    public Error() {
        messages = new ArrayList<>(0);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public Collection<Error> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return Error.class.getSimpleName() + " {" +
                "field=" + field +
                ", messages=" + messages +
                "}";
    }

}
