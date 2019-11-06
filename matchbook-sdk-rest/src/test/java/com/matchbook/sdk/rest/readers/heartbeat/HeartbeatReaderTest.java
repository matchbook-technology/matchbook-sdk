package com.matchbook.sdk.rest.readers.heartbeat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.dtos.heartbeat.ActionPerformed;
import com.matchbook.sdk.rest.dtos.heartbeat.Heartbeat;
import com.matchbook.sdk.rest.readers.ResponseReaderTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HeartbeatReaderTest extends ResponseReaderTest<HeartbeatReader> {

    @Override
    protected HeartbeatReader newReader() {
        return new HeartbeatReader();
    }

    @Test
    @DisplayName("Read next item")
    void readNextItemTest() {
        when(parser.isEndOfObject()).thenReturn(false, false, false, true);
        when(parser.getFieldName()).thenReturn("action-performed", "actual-timeout", "timeout-time");
        when(parser.getEnum(ActionPerformed.class)).thenReturn(ActionPerformed.CANCELLING_OFFERS);
        when(parser.getInteger()).thenReturn(60);
        Instant timeoutTime = Instant.now();
        when(parser.getInstant()).thenReturn(timeoutTime);

        Heartbeat heartbeat = unit.readNextItem();

        assertThat(heartbeat).isNotNull();
        assertThat(heartbeat.getActionPerformed()).isEqualTo(ActionPerformed.CANCELLING_OFFERS);
        assertThat(heartbeat.getActualTimeout()).isEqualTo(60);
        assertThat(heartbeat.getTimeoutTime()).isCloseTo(timeoutTime, within(1L, ChronoUnit.SECONDS));
        assertThat(unit.hasMoreItems()).isFalse();
    }

}
