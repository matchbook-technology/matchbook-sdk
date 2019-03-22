package com.matchbook.sdk.core.clients.rest.dtos;

import java.util.List;

public interface RestResponse<T> {

    List<T> getContent();

}
