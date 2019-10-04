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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HttpClientWrapperTest {

    @Mock
    private OkHttpClient okHttpClient;

    @Captor
    private ArgumentCaptor<Request> requestArgumentCaptor;

    private HttpClientWrapper unit;

    @Before
    public void setUp() {
        Call call = mock(Call.class);
        doNothing().when(call).enqueue(any(Callback.class));
        when(okHttpClient.newCall(any(Request.class))).thenReturn(call);

        unit = new HttpClientWrapper(okHttpClient);
    }

    @Test
    public void getTest() {
        String url = "http://mydomain.com/";
        HttpCallback httpCallback = mock(HttpCallback.class);
        unit.get(url, httpCallback);

        verify(okHttpClient).newCall(requestArgumentCaptor.capture());
        Request capturedRequest = requestArgumentCaptor.getValue();
        verifyHttpRequest(capturedRequest, url, "GET");
    }

    @Test
    public void postTest() {
        String url = "http://mydomain.com/";
        HttpCallback httpCallback = mock(HttpCallback.class);
        unit.post(url, "{}", httpCallback);

        verify(okHttpClient).newCall(requestArgumentCaptor.capture());
        Request capturedRequest = requestArgumentCaptor.getValue();
        verifyHttpRequest(capturedRequest, url, "POST");
    }

    @Test
    public void putTest() {
        String url = "http://mydomain.com/";
        HttpCallback httpCallback = mock(HttpCallback.class);
        unit.put(url, "{}", httpCallback);

        verify(okHttpClient).newCall(requestArgumentCaptor.capture());
        Request capturedRequest = requestArgumentCaptor.getValue();
        verifyHttpRequest(capturedRequest, url, "PUT");
    }

    @Test
    public void deleteTest() {
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
