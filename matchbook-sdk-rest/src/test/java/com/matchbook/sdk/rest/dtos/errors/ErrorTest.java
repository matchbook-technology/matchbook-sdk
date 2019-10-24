package com.matchbook.sdk.rest.dtos.errors;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestResponseTest;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ErrorTest extends RestResponseTest<Error> {

    @Override
    protected Error newResponse() {
        return new Error();
    }

    @Test
    @DisplayName("Set and get field")
    void fieldTest() {
        String field = "stake";
        unit.setField(field);
        assertThat(unit.getField()).isEqualTo(field);
    }

    @Test
    @DisplayName("Set and get messages")
    void messagesTest() {
        String errorMessage = "Stake must be at least 0.01 EUR";
        unit.setMessages(Collections.singletonList(errorMessage));
        assertThat(unit.getMessages()).containsOnly(errorMessage);
    }

}
