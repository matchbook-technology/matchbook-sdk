package com.matchbook.sdk.core.dtos;

import java.util.Collection;

public interface RestResponse<T> {

    Collection<T> getContent();

}
