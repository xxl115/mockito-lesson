package com.young.bean.mockito.lesson06;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpyAnnotationsTest {

    @Spy
    private final List<String> list = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSpy() {
        list.add("Mockito");
        list.add("PowerMock");
        assertEquals(list.get(0), "Mockito");
        assertEquals(list.get(1), "PowerMock");

        when(list.isEmpty()).thenReturn(true);
        when(list.size()).thenReturn(1);


        assertEquals(list.get(0), "Mockito");
        assertEquals(list.get(1), "PowerMock");
        assertEquals(list.size(), 1);
        assertTrue(list.isEmpty());


    }
}
