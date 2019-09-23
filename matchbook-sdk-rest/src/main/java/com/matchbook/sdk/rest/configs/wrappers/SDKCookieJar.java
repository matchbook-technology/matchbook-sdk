package com.matchbook.sdk.rest.configs.wrappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Implementation of {@link CookieJar} to hold the cookies in memory. These are stored based on the host of the request.
 */
public class SDKCookieJar implements CookieJar {

    public static final String MB_CLIENT_TYPE_COOKIE_NAME = "mb-client-type";
    public static final String MB_SDK_COOKIE_VALUE = "mb-sdk";

    private final Map<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();
    private final List<Cookie> alwaysIncludedCookies = new ArrayList<>();

    {
        Cookie mbClientCookie = new Cookie.Builder()
            .path("/")
            .domain(".")
            .name(MB_CLIENT_TYPE_COOKIE_NAME)
            .value(MB_SDK_COOKIE_VALUE)
            .build();
        alwaysIncludedCookies.add(mbClientCookie);
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null) {
            cookieStore.put(url.host(), cookies);
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> returnCookies = new ArrayList<>(alwaysIncludedCookies);
        List<Cookie> storedCookies = cookieStore.get(url.host());

        if (storedCookies == null) {
            return returnCookies;
        }

        final long now = System.currentTimeMillis();

        // filter out expired cookies
        List<Cookie> unexpiredCookies = storedCookies.stream()
            .filter(cookie -> cookie.expiresAt() > now)
            .collect(Collectors.toList());

        if (storedCookies.size() > unexpiredCookies.size()) {
            cookieStore.put(url.host(), unexpiredCookies);
        }

        returnCookies.addAll(unexpiredCookies);
        return returnCookies;
    }
}
