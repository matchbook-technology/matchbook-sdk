package com.matchbook.sdk.rest.configs.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Implementation of {@link CookieJar} to hold the cookies in memory. These are stored based on the host of the request.
 */
class SDKCookieJar implements CookieJar {

    private static final String MB_CLIENT_TYPE_COOKIE_NAME = "mb-client-type";
    private static final String MB_SDK_COOKIE_VALUE = "mb-sdk";

    private final Map<String, List<Cookie>> cookieStore;
    private final List<Cookie> alwaysIncludedCookies;

    SDKCookieJar() {
        cookieStore = new ConcurrentHashMap<>();

        Cookie mbClientCookie = new Cookie.Builder()
            .path("/")
            .domain(".")
            .name(MB_CLIENT_TYPE_COOKIE_NAME)
            .value(MB_SDK_COOKIE_VALUE)
            .build();
        alwaysIncludedCookies = Collections.singletonList(mbClientCookie);
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (Objects.nonNull(cookies)) {
            cookieStore.put(url.host(), cookies);
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> requestCookies = new ArrayList<>(alwaysIncludedCookies);
        final String host = url.host();
        if (cookieStore.containsKey(host)) {
            List<Cookie> storedCookies = cookieStore.get(host);

            // filter out expired cookies
            final long now = System.currentTimeMillis();
            List<Cookie> unexpiredCookies = storedCookies.stream()
                .filter(cookie -> cookie.expiresAt() > now)
                .collect(Collectors.toList());
            if (storedCookies.size() > unexpiredCookies.size()) {
                if (!unexpiredCookies.isEmpty()) {
                    cookieStore.put(host, unexpiredCookies);
                } else {
                    cookieStore.remove(host);
                }
            }
            requestCookies.addAll(unexpiredCookies);
        }
        return requestCookies;
    }

}
