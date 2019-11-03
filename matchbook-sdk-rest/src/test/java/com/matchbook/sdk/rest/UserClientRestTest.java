package com.matchbook.sdk.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.rest.configs.ClientConfig;
import com.matchbook.sdk.rest.configs.ConnectionManager;
import com.matchbook.sdk.rest.configs.HttpClient;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.user.Account;
import com.matchbook.sdk.rest.dtos.user.Balance;
import com.matchbook.sdk.rest.dtos.user.Login;
import com.matchbook.sdk.rest.dtos.user.LoginRequest;
import com.matchbook.sdk.rest.dtos.user.Logout;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
class UserClientRestTest {

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private ClientConfig clientConfig;

    @Mock
    private HttpClient httpClient;

    @Mock
    private Serializer serializer;

    private UserClientRest unit;

    @BeforeEach
    void setUp() {
        when(connectionManager.getClientConfig()).thenReturn(clientConfig);
        when(connectionManager.getSerializer()).thenReturn(serializer);
        when(connectionManager.getHttpClient()).thenReturn(httpClient);

        unit = new UserClientRest(connectionManager);
    }

    @Test
    @DisplayName("POST login")
    void loginTest() throws IOException {
        when(clientConfig.getLoginUrl()).thenReturn("https://matchbook.example.com/login");
        when(clientConfig.getUsername()).thenReturn("john_doe".toCharArray());
        when(clientConfig.getPassword()).thenReturn("secret".toCharArray());
        when(serializer.writeObjectAsString(any(LoginRequest.class))).thenReturn("{}");
        StreamObserver<Login> streamObserver = (StreamObserver<Login>) mock(StreamObserver.class);

        unit.login(streamObserver);

        verify(httpClient).post(eq("https://matchbook.example.com/login"), anyString(), any(ResponseCallback.class));
    }

    @Test
    @DisplayName("DELETE logout")
    void logoutTest() {
        when(clientConfig.getLoginUrl()).thenReturn("https://matchbook.example.com/login");
        StreamObserver<Logout> streamObserver = (StreamObserver<Logout>) mock(StreamObserver.class);

        unit.logout(streamObserver);

        verify(httpClient).delete(eq("https://matchbook.example.com/login"), any(ResponseCallback.class));
    }

    @Test
    @DisplayName("GET account")
    void getAccountTest() {
        when(clientConfig.getSportsUrl()).thenReturn("https://matchbook.example.com/sports");
        StreamObserver<Account> streamObserver = (StreamObserver<Account>) mock(StreamObserver.class);

        unit.getAccount(streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/account"), any(ResponseCallback.class));
    }

    @Test
    @DisplayName("GET balance")
    void getBalanceTest() {
        when(clientConfig.getSportsUrl()).thenReturn("https://matchbook.example.com/sports");
        StreamObserver<Balance> streamObserver = (StreamObserver<Balance>) mock(StreamObserver.class);

        unit.getBalance(streamObserver);

        verify(httpClient).get(eq("https://matchbook.example.com/sports/account/balance"), any(ResponseCallback.class));
    }

}
