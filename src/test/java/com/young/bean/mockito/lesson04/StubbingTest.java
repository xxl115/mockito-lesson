package com.young.bean.mockito.lesson04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class StubbingTest {

    private ArrayList list;

    @Before
    public void init() {
        this.list = mock(ArrayList.class);
    }

    @Test
    public void howToUseStubbing() {
        when(list.get(0)).thenReturn("first");
        assertEquals(list.get(0), "first");

        when(list.get(anyInt())).thenThrow(new RuntimeException());
        try {
            list.get(0);
            fail();
        } catch (Throwable e) {
//            assertEquals(e, new RuntimeException());
            assertThrows(RuntimeException.class, (ThrowingRunnable) e.getCause());
            assertThat(e, instanceOf(RuntimeException.class));
        }

    }

    @Test
    public void howToStubbingVoidMethod() {
        doNothing().when(list).clear();
        list.clear();
        verify(list,times(1)).clear();

        doThrow(RuntimeException.class).when(list).clear();

        try {
            list.clear();
            fail();
        } catch (Exception e) {
            assertThrows(RuntimeException.class, (ThrowingRunnable) e.getCause());
        }
    }

    @Test
    public void stubbingDoReturn() {

        when(list.get(0)).thenReturn("first");
        doReturn("second").when(list).get(1);
        System.out.println(list.get(0));
        System.out.println(list.get(1));

        assertEquals(list.get(0), "first");
        assertEquals(list.get(1), "second");
    }

    @Test
    public void iterateStubbing() {
//        when(list.size()).thenReturn(1,2,3,4);
        when(list.size()).thenReturn(1,2).thenReturn(3).thenReturn(4);
//        when(list.size()).thenReturn(2);
//        when(list.size()).thenReturn(3);
//        when(list.size()).thenReturn(4);
        assertEquals(list.size(), 1);
        assertEquals(list.size(), 2);
        assertEquals(list.size(), 3);
        assertEquals(list.size(), 4);

    }

    @Test
    public void stubbingWithAnswer() {
        when(list.get(anyInt())).thenAnswer(invocationOnMock ->
                {
                    Integer index = invocationOnMock.getArgumentAt(0, Integer.class);
                    return String.valueOf(index * 10);
                }
        );

        assertEquals(list.get(0),"0");
        assertEquals(list.get(10),"100");

    }

    @Test
    public void stubbingWithRealCall() {
        StubbingService service = mock(StubbingService.class);
        System.out.println(service.getClass());
        service.getS();
        System.out.println(service.getI());
        when(service.getS()).thenReturn("alex");
        assertEquals(service.getS(),"alex");

        when(service.getI()).thenCallRealMethod();
        assertEquals(service.getI(), 10);
    }

    @After
    public void destroy() {
        reset(list);

    }
}
