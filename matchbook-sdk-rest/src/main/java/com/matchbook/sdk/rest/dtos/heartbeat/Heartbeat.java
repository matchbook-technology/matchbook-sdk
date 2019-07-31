package com.matchbook.sdk.rest.dtos.heartbeat;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

import com.matchbook.sdk.rest.dtos.RestResponse;

public class Heartbeat implements RestResponse<Heartbeat> {

    private ActionPerformed actionPerformed;
    private Integer actualTimeout;
    private Instant timeoutTime;

    public ActionPerformed getActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(ActionPerformed actionPerformed) {
        this.actionPerformed = actionPerformed;
    }

    public Integer getActualTimeout() {
        return actualTimeout;
    }

    public void setActualTimeout(Integer actualTimeout) {
        this.actualTimeout = actualTimeout;
    }

    public Instant getTimeoutTime() {
        return timeoutTime;
    }

    public void setTimeoutTime(Instant timeoutTime) {
        this.timeoutTime = timeoutTime;
    }

    @Override
    public Set<Heartbeat> getContent() {
        return Collections.singleton(this);
    }

    @Override
    public String toString() {
        return Heartbeat.class.getSimpleName() + " {" +
                "actionPerformed=" + actionPerformed +
                ", actualTimeout=" + actualTimeout +
                ", timeoutTime=" + timeoutTime +
                "}";
    }

}
