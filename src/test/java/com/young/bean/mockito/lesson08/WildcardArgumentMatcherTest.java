package com.young.bean.mockito.lesson08;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class WildcardArgumentMatcherTest {

    @Mock
    private SimpleService simpleService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void wildcardMethod1() {

//        SimpleService simpleService = mock(SimpleService.class);

        when(simpleService.method(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(100);
        int result = simpleService.method(1, "Alex", Collections.emptyList(), "Mockito");
        assertEquals(100, result);
    }

    @Test
    public void wildcardMethodWithSpec() {
        when(simpleService.method(anyInt(), eq("Alex"), anyCollection(), isA(Serializable.class))).thenReturn(100);
        when(simpleService.method(anyInt(), eq("Wang"), anyCollection(), isA(Serializable.class))).thenReturn(200);

        int result = simpleService.method(1, "Alex", Collections.emptyList(), "Mockito");
        assertEquals(100, result);

        result = simpleService.method(1, "Wang", Collections.emptyList(), "Mockito");
        assertEquals(200, result);

        result = simpleService.method(1, "asdf", Collections.emptyList(), "Mockito");
        assertEquals(0, result);

    }


    @Test
    public void wildcardMethod2() {
        doNothing().when(simpleService).method2(anyInt(), anyString(), anyCollection(), isA(Serializable.class));
        simpleService.method2(1, "Alex", Collections.emptyList(), "Mockito");
        verify(simpleService, times(1)).method2(1, "Alex", Collections.emptyList(), "Mockito");
        verify(simpleService, times(1)).method2(anyInt(), eq("Alex"), eq(Collections.emptyList()), isA(Serializable.class));
    }

    @After
    public void destroy() {
//        reset(simpleService);
    }
}
