package com.matchbook.sdk.core.clients.rest.dtos.events;

import com.matchbook.sdk.core.clients.rest.dtos.RestResponse;

import java.util.Collections;
import java.util.Set;

public class Sport implements RestResponse<Sport> {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<Sport> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return Sport.class.getSimpleName() + " {" +
                "id=" + id +
                ", name=" + name +
                "}";
    }

}
