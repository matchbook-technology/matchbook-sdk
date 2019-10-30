package com.matchbook.sdk.rest.configs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConnectionManagerTest {

    @Mock
    private ClientConfig clientConfig;

    @Mock
    private HttpConfig httpConfig;

    private ConnectionManager unit;

    @BeforeEach
    void setUp() {
        when(clientConfig.getHttpConfig()).thenReturn(httpConfig);

        unit = new ConnectionManager.Builder(clientConfig)
                .autoManageSession(false)
                .build();
    }

    @AfterEach
    void tearDown() {
        unit.close();
    }

    @Test
    @DisplayName("Check client configuration")
    void clientConfigTest() {
        assertThat(unit.getClientConfig()).isEqualTo(clientConfig);
    }

    @Test
    @DisplayName("Check HTTP configuration")
    void httpClientTest() {
        assertThat(unit.getHttpClient()).isNotNull();
    }

    @Test
    @DisplayName("Check serializer")
    void serializerTest() {
        assertThat(unit.getSerializer()).isNotNull();
    }

    @Test
    @DisplayName("Check is session auto-managed")
    void isSessionAutoManagedTest() {
        assertThat(unit.isSessionAutoManaged()).isFalse();
    }

}
