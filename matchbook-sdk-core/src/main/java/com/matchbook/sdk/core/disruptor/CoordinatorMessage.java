package com.matchbook.sdk.core.disruptor;

import java.util.Objects;

import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.model.dataobjects.auth.Account;

public class CoordinatorMessage extends AbstractDisruptorMessage {

    private Login loginDTO;
    private Account account;

    @Override
    public String getId() {
        if (Objects.nonNull(messageType)) {
            String id;
            switch (messageType) {
                case AUTH:
                    id = loginDTO.getUserId().toString();
                    break;
                default:
                    id = "null";
            }
            return messageType.name() + "_" + id;
        }
        return "null";
    }

    @Override
    public void reset() {
        setMessageType(null);
        setSkipped(false);
        
        account = null;
        loginDTO = null;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Login getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(Login loginDTO) {
        this.loginDTO = loginDTO;
    }
}
