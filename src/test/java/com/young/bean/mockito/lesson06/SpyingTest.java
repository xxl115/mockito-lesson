package com.young.bean.mockito.lesson06;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpyingTest {

    @Test
    public void testSpy() {
        List<String> realList = new ArrayList<>();
        List<String> list = spy(realList);
        list.add("Mockito");
        list.add("PowerMock");
        assertEquals(list.get(0), "Mockito");
        assertEquals(list.get(1), "PowerMock");

        when(list.isEmpty()).thenReturn(true);
        when(list.size()).thenReturn(1);


        assertEquals(list.get(0), "Mockito");
        assertEquals(list.get(1), "PowerMock");
        assertEquals(list.size(),1);
        assertTrue(list.isEmpty());


    }
}
