package com.matchbook.sdk.rest.dtos.heartbeat;

import com.matchbook.sdk.rest.dtos.RestResponse;

import java.time.Instant;

public class Heartbeat implements RestResponse {

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
    public String toString() {
        return Heartbeat.class.getSimpleName() + " {" +
                "actionPerformed=" + actionPerformed +
                ", actualTimeout=" + actualTimeout +
                ", timeoutTime=" + timeoutTime +
                "}";
    }

}
