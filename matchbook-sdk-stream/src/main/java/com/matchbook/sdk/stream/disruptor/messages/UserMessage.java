package com.matchbook.sdk.stream.disruptor.messages;

import com.matchbook.sdk.stream.model.dataobjects.user.Balance;

public class UserMessage extends AbstractDisruptorMessage {

    private static final long serialVersionUID = -8425010391202208013L;

    private Balance balance;
    private com.matchbook.sdk.core.dtos.user.Balance balanceDTO;

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public com.matchbook.sdk.core.dtos.user.Balance getBalanceDTO() {
        return balanceDTO;
    }

    public void setBalanceDTO(com.matchbook.sdk.core.dtos.user.Balance balanceDTO) {
        this.balanceDTO = balanceDTO;
    }

    @Override
    public String toString() {
        return UserMessage.class.getSimpleName() + " {" +
                "balance=" + balance +
                ", balanceDTO=" + balanceDTO +
                "}";
    }
}
