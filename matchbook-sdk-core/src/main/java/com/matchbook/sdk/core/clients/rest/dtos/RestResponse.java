package com.matchbook.sdk.core.clients.rest.dtos;

import java.util.Collection;

public interface RestResponse<T> {

    Collection<T> getContent();

}
