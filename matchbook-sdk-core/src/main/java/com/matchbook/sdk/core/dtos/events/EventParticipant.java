package com.matchbook.sdk.core.dtos.events;

public class EventParticipant {

    private Long id;
    private Long eventId;
    private String participantName;
    private String jockeyName;
    private String trainerName;
    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getJockeyName() {
        return jockeyName;
    }

    public void setJockeyName(String jockeyName) {
        this.jockeyName = jockeyName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return EventParticipant.class.getSimpleName() + " {" +
                "id=" + id +
                ", eventId=" + eventId +
                ", participantName=" + participantName +
                ", jockeyName=" + jockeyName +
                ", trainerName=" + trainerName +
                ", number=" + number +
                "}";
    }

}
