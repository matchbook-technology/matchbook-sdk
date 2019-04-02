package com.matchbook.sdk.core.model.mappers;

public interface Mapper<A, B> {

    A mapToModel(B externalModel);

}
