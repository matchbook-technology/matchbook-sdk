package com.matchbook.sdk.rest.configs.wrappers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.configs.HttpConfig;
import com.matchbook.sdk.rest.configs.HttpCallback;
import com.matchbook.sdk.rest.configs.HttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpClientWrapper implements HttpClient {

    private static final String HTTP_HEADER_ACCEPT = "Accept";
    private static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    private static final String MEDIA_TYPE_JSON = "application/json";

    private final OkHttpClient httpClient;
    private final MediaType jsonMediaType;

    public HttpClientWrapper(HttpConfig httpConfig) {
        httpClient = initHttpClient(httpConfig);
        jsonMediaType = MediaType.parse(MEDIA_TYPE_JSON);
    }

    private OkHttpClient initHttpClient(HttpConfig httpConfig) {
        return new OkHttpClient.Builder()
                .connectTimeout(httpConfig.getConnectionTimeoutInMillis(), TimeUnit.MILLISECONDS)
                .writeTimeout(httpConfig.getWriteTimeoutInMillis(), TimeUnit.MILLISECONDS)
                .readTimeout(httpConfig.getReadTimeoutInMillis(), TimeUnit.MILLISECONDS)
                .followRedirects(false)
                .cookieJar(new SDKCookieJar())
                .build();
    }

    @VisibleForTesting
    HttpClientWrapper(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        jsonMediaType = MediaType.parse(MEDIA_TYPE_JSON);
    }

    @Override
    public void get(String url, HttpCallback httpCallback) throws MatchbookSDKHttpException {
        Request request = buildRequest(url)
                .get()
                .build();
        sendHttpRequest(request, new RequestCallback(httpCallback));
    }

    @Override
    public void post(String url, String body, HttpCallback httpCallback) throws MatchbookSDKHttpException {
        Request request = buildRequest(url)
                .post(RequestBody.create(body, jsonMediaType))
                .build();
        sendHttpRequest(request, new RequestCallback(httpCallback));
    }

    @Override
    public void put(String url, String body, HttpCallback httpCallback) throws MatchbookSDKHttpException {
        Request request = buildRequest(url)
                .put(RequestBody.create(body, jsonMediaType))
                .build();
        sendHttpRequest(request, new RequestCallback(httpCallback));
    }

    @Override
    public void delete(String url, HttpCallback httpCallback) throws MatchbookSDKHttpException {
        Request request = buildRequest(url)
                .delete()
                .build();
        sendHttpRequest(request, new RequestCallback(httpCallback));
    }

    private Request.Builder buildRequest(String url) {
        return new Request.Builder()
                .addHeader(HTTP_HEADER_CONTENT_TYPE, jsonMediaType.toString())
                .addHeader(HTTP_HEADER_ACCEPT, jsonMediaType.toString())
                .url(url);
    }

    private void sendHttpRequest(Request request, Callback callback) {
        httpClient.newCall(request).enqueue(callback);
    }

    @Override
    public void close() {
        httpClient.dispatcher().executorService().shutdown();
        httpClient.connectionPool().evictAll();
    }

    private static class RequestCallback implements Callback {

        private final HttpCallback httpCallback;

        private RequestCallback(HttpCallback httpCallback) {
            this.httpCallback = httpCallback;
        }

        @Override
        public void onResponse(Call call, Response response) {
            ResponseBody responseBody = response.body();
            Objects.requireNonNull(responseBody, "The content of the HTTP response cannot be null.");

            InputStream responseInputStream = responseBody.byteStream();
            if (response.isSuccessful()) {
                httpCallback.onSuccessfulResponse(responseInputStream);
            } else {
                httpCallback.onFailedResponse(responseInputStream, response.code());
            }
        }

        @Override
        public void onFailure(Call call, IOException exception) {
            httpCallback.onException(exception);
        }

    }

}
