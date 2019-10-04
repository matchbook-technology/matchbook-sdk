package com.matchbook.sdk.rest.dtos.events;

import com.matchbook.sdk.rest.dtos.RestResponse;

public class Sport implements RestResponse {

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
    public String toString() {
        return Sport.class.getSimpleName() + " {" +
                "id=" + id +
                ", name=" + name +
                "}";
    }

}
