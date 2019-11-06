package com.matchbook.sdk.rest.workers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.UserClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SessionKeepAliveTaskTest {

    @Mock
    private UserClient userClient;

    private SessionKeepAliveTask unit;

    @BeforeEach
    void setUp() {
        unit = new SessionKeepAliveTask(userClient);
    }

    @SuppressWarnings("unchecked")
    @Test
    @DisplayName("Run session keep alive task")
    void runTest() {
        unit.run();

        verify(userClient).login(any(StreamObserver.class));
    }

}
