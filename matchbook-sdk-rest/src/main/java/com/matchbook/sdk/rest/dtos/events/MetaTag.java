package com.matchbook.sdk.rest.dtos.events;

public class MetaTag {

    private Long id;
    private String name;
    private TagType type;
    private String urlName;

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

    public TagType getType() {
        return type;
    }

    public void setType(TagType type) {
        this.type = type;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public String toString() {
        return MetaTag.class.getSimpleName() + " {" +
                "id=" + id +
                ", name=" + name +
                ", tagType=" + type +
                ", urlName=" + urlName +
                "}";
    }
}
