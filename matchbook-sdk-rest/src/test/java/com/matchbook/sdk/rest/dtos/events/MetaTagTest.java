package com.matchbook.sdk.rest.dtos.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MetaTagTest {

    private MetaTag unit;

    @BeforeEach
    void setUp() {
        unit = new MetaTag();
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

    @Test
    @DisplayName("Set and get type")
    void typeTest() {
        TagType tagType = TagType.SPORT;
        unit.setType(tagType);
        assertThat(unit.getType()).isEqualTo(tagType);
    }

    @Test
    @DisplayName("Set and get URL name")
    void urlNameTest() {
        String urlName = "soccer";
        unit.setUrlName(urlName);
        assertThat(unit.getUrlName()).isEqualTo(urlName);
    }

    @Test
    @DisplayName("Object description")
    void toStringTest() {
        String objectDescription = unit.toString();
        assertThat(objectDescription).startsWith(MetaTag.class.getSimpleName());
    }

}
