package com.matchbook.sdk.rest.configs.wrappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SDKCookieJarTest {

    private static final String COOKIE_NAME = "mb-client-type";
    private static final String COOKIE_VALUE = "mb-sdk";

    private SDKCookieJar unit;

    @BeforeEach
    void setUp() {
        unit = new SDKCookieJar();
    }

    @Test
    void saveFromResponseAndLoadForRequestTest() {
        HttpUrl httpUrl = newHttpUrl();
        Cookie cookie1 = buildCookie("cookie1", "value1").build();
        Cookie cookie2 = buildCookie("cookie2", "value2").build();
        List<Cookie> cookies = Arrays.asList(cookie1, cookie2);
        unit.saveFromResponse(httpUrl, cookies);

        List<Cookie> requestCookies = unit.loadForRequest(httpUrl);
        assertThat(requestCookies).isNotNull();
        assertThat(requestCookies).hasSize(3);
        assertThat(requestCookies).extracting("name", "value")
                .containsExactlyInAnyOrder(
                        tuple(COOKIE_NAME, COOKIE_VALUE),
                        tuple(cookie1.name(), cookie1.value()),
                        tuple(cookie2.name(), cookie2.value())
                );
    }

    @Test
    void saveFromResponseAndLoadForRequestNoCookiesTest() {
        HttpUrl httpUrl = newHttpUrl();
        unit.saveFromResponse(httpUrl, null);

        List<Cookie> requestCookies = unit.loadForRequest(httpUrl);
        assertThat(requestCookies).isNotNull();
        assertThat(requestCookies).hasSize(1);
        assertThat(requestCookies).extracting("name", "value")
                .containsExactly(tuple(COOKIE_NAME, COOKIE_VALUE));
    }

    @Test
    void saveFromResponseAndLoadForRequestExpiredCookieTest() {
        HttpUrl httpUrl = newHttpUrl();
        Cookie expiredCookie = buildCookie("cookie", "value")
                .expiresAt(System.currentTimeMillis() - 100L)
                .build();
        List<Cookie> cookies = Collections.singletonList(expiredCookie);
        unit.saveFromResponse(httpUrl, cookies);

        List<Cookie> requestCookies = unit.loadForRequest(httpUrl);
        assertThat(requestCookies).isNotNull();
        assertThat(requestCookies).hasSize(1);
        assertThat(requestCookies).extracting("name", "value")
                .containsExactly(tuple(COOKIE_NAME, COOKIE_VALUE));
    }

    private HttpUrl newHttpUrl() {
        return HttpUrl.get("http://mydomain.com");
    }

    private Cookie.Builder buildCookie(String name, String value) {
        return new Cookie.Builder()
            .path("/")
            .domain("*")
            .name(name)
            .value(value);
    }

}