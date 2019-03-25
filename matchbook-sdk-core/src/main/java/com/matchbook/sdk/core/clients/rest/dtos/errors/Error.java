package com.matchbook.sdk.core.clients.rest.dtos.errors;

import java.util.ArrayList;
import java.util.List;

public class Error {

    private List<String> messages;

    public Error() {
        messages = new ArrayList<>(0);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}
