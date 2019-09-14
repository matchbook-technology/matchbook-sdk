package com.matchbook.sdk.rest.configs.wrappers;

import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.rest.HttpConfig;
import com.matchbook.sdk.rest.configs.HttpCallback;
import com.matchbook.sdk.rest.configs.HttpClient;
import com.matchbook.sdk.rest.configs.Parser;
import com.matchbook.sdk.rest.configs.Serializer;
import com.matchbook.sdk.rest.dtos.errors.Error;
import com.matchbook.sdk.rest.dtos.errors.Errors;
import com.matchbook.sdk.rest.dtos.errors.readers.ErrorsReader;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

            private final Map<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

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

    private static class RequestCallback implements Callback {

        private final HttpCallback httpCallback;
        private final ErrorsReader errorsReader;

        RequestCallback(HttpCallback httpCallback) {
            this.httpCallback = httpCallback;
            errorsReader = new ErrorsReader();
        }

        @Override
        public void onResponse(Call call, Response response) {
            try (ResponseBody responseBody = response.body()) {
                if (response.isSuccessful() && Objects.nonNull(responseBody)) {
                    httpCallback.onResponse(responseBody.byteStream());
                } else {
                    MatchbookSDKHttpException matchbookException = getExceptionForResponse(response);
                    httpCallback.onFailure(matchbookException);
                }
            }
        }

        @Override
        public void onFailure(Call call, IOException exception) {
            MatchbookSDKHttpException matchbookException = new MatchbookSDKHttpException(exception.getMessage(), exception);
            httpCallback.onFailure(matchbookException);
        }

        private MatchbookSDKHttpException getExceptionForResponse(Response response) {
            try {
                ResponseBody responseBody = response.body();
                byte[] responseBytes = Objects.nonNull(responseBody) ? responseBody.bytes() : null;
                if (Objects.nonNull(responseBytes) && responseBytes.length > 0) {
                    if (isAuthenticationErrorPresent(responseBytes)) {
                        return unauthenticatedException();
                    } else {
                        return httpException(response.code(), responseBytes);
                    }
                }
            } catch (IOException e) {
                // do nothing
            }
            return httpException(response.code());
        }

        private boolean isAuthenticationErrorPresent(byte[] responseBytes) {
            return new String(responseBytes).toLowerCase().contains("cannot login");
        }

        private MatchbookSDKHttpException unauthenticatedException() {
            return new MatchbookSDKHttpException("Unable to authenticate user: invalid credentials", ErrorType.UNAUTHENTICATED);
        }

        private MatchbookSDKHttpException httpException(int responseCode) {
            return httpException(responseCode, null);
        }

        private MatchbookSDKHttpException httpException(int responseCode, byte[] responseBytes) {
            String responseCodeMessage = "Unexpected HTTP code " + responseCode;
            Error error = readFirstError(responseBytes);
            if (Objects.nonNull(error)) {
                String errorMessage = error.getMessages().get(0);
                return new MatchbookSDKHttpException(responseCodeMessage, new MatchbookSDKHttpException(errorMessage));
            } else {
                return new MatchbookSDKHttpException(responseCodeMessage);
            }
        }

        private Error readFirstError(byte[] responseBytes) {
            if (Objects.nonNull(responseBytes) && responseBytes.length > 0) {
                Serializer serializer = httpCallback.getSerializer();
                try (Parser parser = serializer.newParser(new ByteArrayInputStream(responseBytes))) {
                    errorsReader.startReading(parser);
                    Errors errors = errorsReader.readFullResponse();
                    if (!errors.getErrors().isEmpty()) {
                        return errors.getErrors().get(0);
                    }
                } catch (IOException e) {
                    // do nothing
                }
            }
            return null;
        }

    }

}
