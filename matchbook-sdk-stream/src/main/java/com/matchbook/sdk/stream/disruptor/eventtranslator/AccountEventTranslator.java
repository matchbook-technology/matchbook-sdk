package com.matchbook.sdk.stream.disruptor.eventtranslator;

import com.lmax.disruptor.EventTranslator;
import com.matchbook.sdk.stream.disruptor.messages.UserMessage;

public class AccountEventTranslator implements EventTranslator<UserMessage> {

    private final UserMessage event;

    public AccountEventTranslator(UserMessage event) {
        this.event = event;
    }

    @Override
    public void translateTo(UserMessage event, long sequence) {
        event.setId(sequence);
        event.setBalanceDTO(this.event.getBalanceDTO());
        event.setBalance(this.event.getBalance());
    }
}
