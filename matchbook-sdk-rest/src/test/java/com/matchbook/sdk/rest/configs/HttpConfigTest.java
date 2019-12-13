package com.matchbook.sdk.rest.configs;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchbook.sdk.rest.configs.HttpConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HttpConfigTest {

    private static long DEFAULT_TIMEOUT = 10_000L;

    private long connectionTimeoutMillis;
    private long writeTimeoutMillis;
    private long readTimeoutMillis;

    private HttpConfig unit;
    private HttpConfig defaultUnit;

    @BeforeEach
    void setUp() {
        connectionTimeoutMillis = 1_000L;
        writeTimeoutMillis = 5_000L;
        readTimeoutMillis = 2_000L;

        unit = new HttpConfig.Builder()
                .connectionTimeoutInMillis(connectionTimeoutMillis)
                .writeTimeoutInMillis(writeTimeoutMillis)
                .readTimeoutInMillis(readTimeoutMillis)
                .build();
        defaultUnit = new HttpConfig.Builder().build();
    }

    @Test
    @DisplayName("Check connection timeout")
    void connectionTimeoutTest() {
        assertThat(unit.getConnectionTimeoutInMillis()).isEqualTo(connectionTimeoutMillis);
        assertThat(defaultUnit.getConnectionTimeoutInMillis()).isEqualTo(DEFAULT_TIMEOUT);
    }

    @Test
    @DisplayName("Check write timeout")
    void writeTimeoutTest() {
        assertThat(unit.getWriteTimeoutInMillis()).isEqualTo(writeTimeoutMillis);
        assertThat(defaultUnit.getWriteTimeoutInMillis()).isEqualTo(DEFAULT_TIMEOUT);
    }

    @Test
    @DisplayName("Check read timeout")
    void readTimeoutTest() {
        assertThat(unit.getReadTimeoutInMillis()).isEqualTo(readTimeoutMillis);
        assertThat(defaultUnit.getReadTimeoutInMillis()).isEqualTo(DEFAULT_TIMEOUT);
    }

    @Test
    @DisplayName("Object description")
    void toStringTest() {
        assertThat(unit.toString()).startsWith(unit.getClass().getSimpleName());
    }

}
