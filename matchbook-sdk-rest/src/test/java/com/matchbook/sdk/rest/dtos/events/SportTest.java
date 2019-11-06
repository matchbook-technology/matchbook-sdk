package com.matchbook.sdk.rest.dtos.events;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.dtos.RestResponseTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SportTest extends RestResponseTest<Sport> {

    @Override
    protected Sport newResponse() {
        return new Sport();
    }

    @Test
    @DisplayName("Set and get ID")
    void idTest() {
        Long id = 15L;
        unit.setId(id);
        assertThat(unit.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Set and get name")
    void nameTest() {
        String name = "Soccer";
        unit.setName(name);
        assertThat(unit.getName()).isEqualTo(name);
    }

}
