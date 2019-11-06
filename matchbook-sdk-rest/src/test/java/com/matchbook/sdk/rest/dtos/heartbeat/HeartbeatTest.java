package com.matchbook.sdk.rest.dtos.heartbeat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.matchbook.sdk.rest.dtos.RestResponseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HeartbeatTest extends RestResponseTest<Heartbeat> {

    @Override
    protected Heartbeat newResponse() {
        return new Heartbeat();
    }

    @Test
    @DisplayName("Set and get action performed")
    void actionPerformedTest() {
        ActionPerformed actionPerformed = ActionPerformed.HEARTBEAT_ACTIVATED;
        unit.setActionPerformed(actionPerformed);
        assertThat(unit.getActionPerformed()).isEqualTo(actionPerformed);
    }

    @Test
    @DisplayName("Set and get actual timeout")
    void actualTimeoutTest() {
        Integer actualTimeout = 30;
        unit.setActualTimeout(actualTimeout);
        assertThat(unit.getActualTimeout()).isEqualTo(actualTimeout);
    }

    @Test
    @DisplayName("Set and get timeout time")
    void timeoutTimeTest() {
        Instant timeoutTime = Instant.now();
        unit.setTimeoutTime(timeoutTime);
        assertThat(unit.getTimeoutTime()).isCloseTo(timeoutTime, within(1L, ChronoUnit.SECONDS));
    }

}
