package com.matchbook.sdk.core.disruptor;

import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.model.dataobjects.auth.User;

public class CoordinatorMessage extends AbstractDisruptorMessage {

    private Login loginDTO;
    private User user;

    @Override
    public void reset() {
        setMessageType(null);
        setSkipped(false);

        user = null;
        loginDTO = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Login getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(Login loginDTO) {
        this.loginDTO = loginDTO;
    }
}
