

package com.young.bean.mockito.mockwebserver;

import com.google.gson.Gson;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.OkHttpTools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test case for {@link OkHttpTools}.
 *
 * @author Young Bean
 */
public class OkHttpToolsTest {

    private MockWebServer server;

    private String url;

    private final String json = "{\"appName\":\"soul\"}";

    @Before
    public void before() throws IOException {
        server = new MockWebServer();
        server.start();
        url = server.url("").url().toString();
    }

    @After
    public void after() throws IOException {
        server.shutdown();
    }

    @Test
    public void testPostReturnString() throws IOException {

        server.enqueue(new MockResponse().setResponseCode(200).setBody(json));

        assertEquals(json, OkHttpTools.getInstance().post(url, json));

    }

    @Test
    public void testPostReturnMap() throws IOException {

        final Map<String, String> map = new HashMap<>();
        map.put("appName", "soul");

        server.enqueue(new MockResponse().setResponseCode(200).setBody(json));

        assertEquals(new Gson().toJson(map), OkHttpTools.getInstance().post(url, map));
    }

    @Test
    public void testGetGson() {
        assertEquals(new Gson().toJson(json), OkHttpTools.getInstance().getGson().toJson(json));
    }

}
