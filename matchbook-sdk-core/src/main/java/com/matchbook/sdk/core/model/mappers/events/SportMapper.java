package com.matchbook.sdk.core.model.mappers.events;

import com.matchbook.sdk.core.model.dataobjects.events.Sport;

public class SportMapper {

    public static Sport mapDtoToModel(com.matchbook.sdk.core.clients.rest.dtos.events.Sport sport) {
        Sport sportModel = new Sport();
        sportModel.setId(sport.getId());
        sportModel.setName(sport.getName());
        return sportModel;
    }
}
