package com.matchbook.sdk.rest.configs.wrappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Test class for {@link SDKCookieJar}.
 *
 */
public class SDKCookieJarTest {

    private CookieJar target = new SDKCookieJar();

    @Test
    public void saveFromResponseAndLoadForRequestTest() {
        HttpUrl httpUrl = HttpUrl.get("http://mydomain.com");
        List<Cookie> cookies = new ArrayList<>();
        Cookie cookie1 = createCookie("cookie1", "value1");
        Cookie cookie2 = createCookie("cookie2", "value2");
        cookies.add(cookie1);
        cookies.add(cookie2);

        target.saveFromResponse(httpUrl, cookies);

        List<Cookie> requestCookies = target.loadForRequest(httpUrl);
        assertNotNull(requestCookies);
        assertEquals(3, requestCookies.size());

        verifyCookieMatch("mb-client-type", "mb-sdk", requestCookies.get(0));
        verifyCookieMatch(cookie1.name(), cookie1.value(), requestCookies.get(1));
        verifyCookieMatch(cookie2.name(), cookie2.value(), requestCookies.get(2));
    }

    private Cookie createCookie(String name, String value) {
        return new Cookie.Builder()
            .path("/")
            .domain("*")
            .name(name)
            .value(value)
            .build();
    }

    private void verifyCookieMatch(String expectedName, String expectedValue, Cookie cookie) {
        assertEquals(expectedName, cookie.name());
        assertEquals(expectedValue, cookie.value());
    }
}