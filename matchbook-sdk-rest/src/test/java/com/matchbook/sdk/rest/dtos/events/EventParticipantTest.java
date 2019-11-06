package com.matchbook.sdk.rest.dtos.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventParticipantTest {

    private EventParticipant unit;

    @BeforeEach
    void setUp() {
        unit = new EventParticipant();
    }

    @Test
    @DisplayName("Set and get ID")
    void idTest() {
        Long id = 410499974420009L;
        unit.setId(id);
        assertThat(unit.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Set and get event ID")
    void eventIdTest() {
        Long eventId = 410499974370010L;
        unit.setEventId(eventId);
        assertThat(unit.getEventId()).isEqualTo(eventId);
    }

    @Test
    @DisplayName("Set and get participant name")
    void participantNameTest() {
        String participantName = "Vinci the Great";
        unit.setParticipantName(participantName);
        assertThat(unit.getParticipantName()).isEqualTo(participantName);
    }

    @Test
    @DisplayName("Set and get jockey name")
    void jockeyNameTest() {
        String jockeyName = "Evan Mullins";
        unit.setJockeyName(jockeyName);
        assertThat(unit.getJockeyName()).isEqualTo(jockeyName);
    }

    @Test
    @DisplayName("Set and get trainer name")
    void trainerNameTest() {
        String trainerName = "John O'Connor";
        unit.setTrainerName(trainerName);
        assertThat(unit.getTrainerName()).isEqualTo(trainerName);
    }

    @Test
    @DisplayName("Set and get number")
    void numberTest() {
        Integer number = 3;
        unit.setNumber(number);
        assertThat(unit.getNumber()).isEqualTo(number);
    }

    @Test
    @DisplayName("Object description")
    void toStringTest() {
        String objectDescription = unit.toString();
        assertThat(objectDescription).startsWith(EventParticipant.class.getSimpleName());
    }

}
