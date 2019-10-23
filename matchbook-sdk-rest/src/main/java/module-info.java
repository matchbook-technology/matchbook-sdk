open module com.matchbook.sdk.rest {

    exports com.matchbook.sdk.rest;
    exports com.matchbook.sdk.rest.dtos;
    exports com.matchbook.sdk.rest.dtos.events;
    exports com.matchbook.sdk.rest.dtos.heartbeat;
    exports com.matchbook.sdk.rest.dtos.offers;
    exports com.matchbook.sdk.rest.dtos.prices;
    exports com.matchbook.sdk.rest.dtos.user;

    requires com.matchbook.sdk.core;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.module.paramnames;
    requires org.slf4j;
    requires okhttp3;

}