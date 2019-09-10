package com.matchbook.sdk.rest.dtos.heartbeat.readers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.ResponseReader;
import com.matchbook.sdk.rest.dtos.heartbeat.ActionPerformed;
import com.matchbook.sdk.rest.dtos.heartbeat.Heartbeat;

public class HeartbeatResponseReader extends ResponseReader<Heartbeat> {

    public HeartbeatResponseReader() {
        super();
    }

    @Override
    protected Heartbeat readItem() throws MatchbookSDKParsingException {
        final Heartbeat heartbeat = new Heartbeat();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("action-performed".equals(fieldName)) {
                heartbeat.setActionPerformed(parser.getEnum(ActionPerformed.class));
            } else if ("actual-timeout".equals(fieldName)) {
                heartbeat.setActualTimeout(parser.getInteger());
            } else if ("timeout-time".equals(fieldName)) {
                heartbeat.setTimeoutTime(parser.getInstant());
            }
            parser.moveToNextToken();
        }
        return heartbeat;
    }

}
