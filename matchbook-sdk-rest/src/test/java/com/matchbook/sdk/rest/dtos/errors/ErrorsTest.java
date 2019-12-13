package com.matchbook.sdk.rest.dtos.errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.matchbook.sdk.rest.dtos.RestResponseTest;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ErrorsTest extends RestResponseTest<Errors> {

    @Override
    protected Errors newResponse() {
        return new Errors();
    }

    @Test
    @DisplayName("Set and get errors")
    void errorsTest() {
        Error error = mock(Error.class);
        unit.setErrors(Collections.singletonList(error));
        assertThat(unit.getErrors()).containsOnly(error);
    }

}
