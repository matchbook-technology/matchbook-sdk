package com.matchbook.sdk.rest.configs.wrappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.rest.configs.HttpCallback;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HttpClientWrapperTest {

    @Mock
    private OkHttpClient okHttpClient;

    @Captor
    private ArgumentCaptor<Request> requestArgumentCaptor;

    private HttpClientWrapper unit;

    @BeforeEach
    void setUp() {
        Call call = mock(Call.class);
        doNothing().when(call).enqueue(any(Callback.class));
        when(okHttpClient.newCall(any(Request.class))).thenReturn(call);

        unit = new HttpClientWrapper(okHttpClient);
    }

    @Test
    @DisplayName("GET request")
    void getTest() {
        String url = "http://mydomain.com/";
        HttpCallback httpCallback = mock(HttpCallback.class);
        unit.get(url, httpCallback);

        verify(okHttpClient).newCall(requestArgumentCaptor.capture());
        Request capturedRequest = requestArgumentCaptor.getValue();
        verifyHttpRequest(capturedRequest, url, "GET");
    }

    @Test
    @DisplayName("POST request")
    void postTest() {
        String url = "http://mydomain.com/";
        HttpCallback httpCallback = mock(HttpCallback.class);
        unit.post(url, "{}", httpCallback);

        verify(okHttpClient).newCall(requestArgumentCaptor.capture());
        Request capturedRequest = requestArgumentCaptor.getValue();
        verifyHttpRequest(capturedRequest, url, "POST");
    }

    @Test
    @DisplayName("PUT request")
    void putTest() {
        String url = "http://mydomain.com/";
        HttpCallback httpCallback = mock(HttpCallback.class);
        unit.put(url, "{}", httpCallback);

        verify(okHttpClient).newCall(requestArgumentCaptor.capture());
        Request capturedRequest = requestArgumentCaptor.getValue();
        verifyHttpRequest(capturedRequest, url, "PUT");
    }

    @Test
    @DisplayName("DELETE request")
    void deleteTest() {
        String url = "http://mydomain.com/";
        HttpCallback httpCallback = mock(HttpCallback.class);
        unit.delete(url, httpCallback);

        verify(okHttpClient).newCall(requestArgumentCaptor.capture());
        Request capturedRequest = requestArgumentCaptor.getValue();
        verifyHttpRequest(capturedRequest, url, "DELETE");
    }

    private void verifyHttpRequest(Request request, String url, String method) {
        assertThat(request).isNotNull();
        assertThat(request.url().toString()).isEqualTo(url);
        assertThat(request.header("Content-Type")).isEqualTo("application/json");
        assertThat(request.header("Accept")).isEqualTo("application/json");
        assertThat(request.method()).isEqualToIgnoringCase(method);
    }

}
