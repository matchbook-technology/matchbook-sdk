package com.matchbook.sdk.core.disruptor.eventtranslator;

import com.lmax.disruptor.EventTranslator;
import com.matchbook.sdk.core.disruptor.messages.UserMessage;

public class AccountEventTranslator implements EventTranslator<UserMessage> {

    private final UserMessage event;

    public AccountEventTranslator(UserMessage event) {
        this.event = event;
    }

    @Override
    public void translateTo(UserMessage event, long sequence) {
        event.setId(sequence);
    }
}
