package com.matchbook.sdk.core.dtos.heartbeat;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import com.matchbook.sdk.core.dtos.RestResponse;

public class Heartbeat implements RestResponse<Heartbeat> {

    private ActionPerformed actionPerformed;
    private Integer actualTimeout;
    private Date timeoutTime;

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

    public Date getTimeoutTime() {
        return timeoutTime;
    }

    public void setTimeoutTime(Date timeoutTime) {
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
