package com.matchbook.sdk.rest.configs.wrappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.rest.HttpConfig;
import com.matchbook.sdk.rest.configs.HttpCallback;
import com.matchbook.sdk.rest.configs.HttpCallback2;
import com.matchbook.sdk.rest.configs.HttpClient;
import com.matchbook.sdk.rest.dtos.Reader;
import com.matchbook.sdk.rest.dtos.RestResponse;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpClientWrapper implements HttpClient {

    private static final String HTTP_HEADER_ACCEPT = "Accept";
    private static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    private static final String MEDIA_TYPE_JSON = "application/json";

    private final OkHttpClient httpClient;
    private final MediaType jsonMediaType;

    public HttpClientWrapper(HttpConfig httpConfig) {
        httpClient = initHttpClient(httpConfig);

        this.jsonMediaType = MediaType.parse(MEDIA_TYPE_JSON);
    }

    private OkHttpClient initHttpClient(HttpConfig httpConfig) {
        CookieJar cookieJar = createCookieJar();
        return new OkHttpClient.Builder()
                .connectTimeout(httpConfig.getConnectionTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(httpConfig.getWriteTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(httpConfig.getReadTimeout(), TimeUnit.MILLISECONDS)
                .followRedirects(false)
                .cookieJar(cookieJar)
                .build();
    }

    /**
     * Create a {@link CookieJar} to hold in memory the cookies. These are stored based on the host of the request.
     *
     * @return a {@link CookieJar} instance
     */
    private CookieJar createCookieJar() {
        return new CookieJar() {

            private final ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> cookies) {
                if (Objects.nonNull(cookies)) {
                    cookieStore.put(httpUrl.host(), cookies);
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                List<Cookie> cookies = cookieStore.get(httpUrl.host());
                if (Objects.isNull(cookies)) {
                    return new ArrayList<>(0);
                }

                // filter out expired cookies
                final long now = System.currentTimeMillis();
                List<Cookie> unexpiredCookies = cookies.stream()
                    .filter(cookie -> cookie.expiresAt() > now)
                    .collect(Collectors.toList());

                if (cookies.size() > unexpiredCookies.size()) {
                    cookieStore.put(httpUrl.host(), unexpiredCookies);
                }
                return unexpiredCookies;
            }
        };
    }

    @Override
    public <T, R extends RestResponse<T>> void get(String url,
            HttpCallback2<T, R> httpCallback,
            Reader<T, R> reader) throws MatchbookSDKHttpException {
        Request request = buildGetRequest(url);
        sendHttpRequest(request, new RequestCallback2<>(httpCallback, reader));
    }

    @Deprecated
    @Override
    public void get(String url, HttpCallback httpCallback) throws MatchbookSDKHttpException {
        Request request = buildGetRequest(url);
        sendHttpRequest(request, new RequestCallback(httpCallback));
    }

    @Override
    public <T, R extends RestResponse<T>> void post(String url,
            String body,
            HttpCallback2<T, R> httpCallback,
            Reader<T, R> reader) throws MatchbookSDKHttpException {
        Request request = buildPostRequest(url, body);
        sendHttpRequest(request, new RequestCallback2<>(httpCallback, reader));
    }

    @Deprecated
    @Override
    public void post(String url, String body, HttpCallback httpCallback) throws MatchbookSDKHttpException {
        Request request = buildPostRequest(url, body);
        sendHttpRequest(request, new RequestCallback(httpCallback));
    }

    @Override
    public <T, R extends RestResponse<T>> void put(String url,
            String body,
            HttpCallback2<T, R> httpCallback,
            Reader<T, R> reader) throws MatchbookSDKHttpException {
        Request request = buildPutRequest(url, body);
        sendHttpRequest(request, new RequestCallback2<>(httpCallback, reader));
    }

    @Deprecated
    @Override
    public void put(String url, String body, HttpCallback httpCallback) throws MatchbookSDKHttpException {
        Request request = buildPutRequest(url, body);
        sendHttpRequest(request, new RequestCallback(httpCallback));
    }

    @Override
    public <T, R extends RestResponse<T>> void delete(String url,
            HttpCallback2<T, R> httpCallback,
            Reader<T, R> reader) throws MatchbookSDKHttpException {
        Request request = buildDeleteRequest(url);
        sendHttpRequest(request, new RequestCallback2<>(httpCallback, reader));
    }

    @Deprecated
    @Override
    public void delete(String url, HttpCallback httpCallback) throws MatchbookSDKHttpException {
        Request request = buildDeleteRequest(url);
        sendHttpRequest(request, new RequestCallback(httpCallback));
    }

    private Request buildGetRequest(String url) {
        return buildRequest(url)
                .get()
                .build();
    }

    private Request buildPostRequest(String url, String body) {
        return buildRequest(url)
                .post(RequestBody.create(body, jsonMediaType))
                .build();
    }

    private Request buildPutRequest(String url, String body) {
        return buildRequest(url)
                .put(RequestBody.create(body, jsonMediaType))
                .build();
    }

    private Request buildDeleteRequest(String url) {
        return buildRequest(url)
                .delete()
                .build();
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

}
