package com.matchbook.sdk.rest.configs.wrappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import org.junit.Before;
import org.junit.Test;

public class SDKCookieJarTest {

    private SDKCookieJar unit;

    @Before
    public void setUp() {
        unit = new SDKCookieJar();
    }

    @Test
    public void saveFromResponseAndLoadForRequestTest() {
        HttpUrl httpUrl = HttpUrl.get("http://mydomain.com");
        List<Cookie> cookies = new ArrayList<>();
        Cookie cookie1 = createCookie("cookie1", "value1");
        Cookie cookie2 = createCookie("cookie2", "value2");
        cookies.add(cookie1);
        cookies.add(cookie2);

        unit.saveFromResponse(httpUrl, cookies);

        List<Cookie> requestCookies = unit.loadForRequest(httpUrl);
        assertNotNull(requestCookies);
        assertEquals(3, requestCookies.size());

        verifyCookie("mb-client-type", "mb-sdk", requestCookies.get(0));
        verifyCookie(cookie1.name(), cookie1.value(), requestCookies.get(1));
        verifyCookie(cookie2.name(), cookie2.value(), requestCookies.get(2));
    }

    private Cookie createCookie(String name, String value) {
        return new Cookie.Builder()
            .path("/")
            .domain("*")
            .name(name)
            .value(value)
            .build();
    }

    private void verifyCookie(String expectedName, String expectedValue, Cookie cookie) {
        assertEquals(expectedName, cookie.name());
        assertEquals(expectedValue, cookie.value());
    }
}