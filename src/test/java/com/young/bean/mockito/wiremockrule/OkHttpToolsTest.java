/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.young.bean.mockito.wiremockrule;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import util.OkHttpTools;
import wiremock.com.google.common.net.HttpHeaders;
import wiremock.org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Young Bean
 */
public class OkHttpToolsTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.wireMockConfig().dynamicPort(), false);

    private String url;

    private final String json = "{\"appName\":\"soul\"}";

    @Before
    public final void setUpWiremock() {
        wireMockRule.stubFor(post(urlPathEqualTo("/"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
                        .withBody(json)
                        .withStatus(200))
        );
        url = "http://localhost:" + wireMockRule.port();
    }

    @Test
    public void testPostReturnString() throws IOException {
        assertEquals(json, OkHttpTools.getInstance().post(url, json));
    }

    @Test
    public void testPostReturnMap() throws IOException {
        final Map<String, String> map = new HashMap<>();
        map.put("appName", "soul");
        assertEquals(json, OkHttpTools.getInstance().post(url, map));
    }

    @Test
    public void testGetGson() {
        assertNotNull(OkHttpTools.getInstance().getGson());
    }
}
