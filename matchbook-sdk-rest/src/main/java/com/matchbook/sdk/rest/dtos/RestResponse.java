package com.matchbook.sdk.rest.dtos;

import java.util.Collection;

public interface RestResponse<T> {

    Collection<T> getContent();

}