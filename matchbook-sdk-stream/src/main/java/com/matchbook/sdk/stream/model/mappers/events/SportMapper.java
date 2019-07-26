package com.matchbook.sdk.stream.model.mappers.events;

import com.matchbook.sdk.stream.model.dataobjects.events.Sport;

public class SportMapper {

    public static Sport mapDtoToModel(com.matchbook.sdk.rest.dtos.events.Sport sport) {
        Sport sportModel = new Sport();
        sportModel.setId(sport.getId());
        sportModel.setName(sport.getName());
        return sportModel;
    }
}
