package com.young.bean.mockito.lesson07;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class ArgumentMatcherTest {

    @Test
    public void basicTest() {
        ArrayList<Integer> list = mock(ArrayList.class);
        when(list.get(eq(0))).thenReturn(100);
        assertEquals(list.get(0), new Integer(100));
        assertNull(list.get(1));
    }

    @Test
    public void testComplex() {
        Foo foo = mock(Foo.class);
        when(foo.function(Mockito.isA(Parent.class))).thenReturn(100);
        int result = foo.function(new Child2());
        assertEquals(result, 100);

        result = foo.function(new Child1());
        assertEquals(result, 100);
        reset(foo);

        when(foo.function(Mockito.any(Child1.class))).thenReturn(10);
        result = foo.function(new Child2());
        assertEquals(result, 10);
    }


    static class Foo{
        int function(Parent p){
            return p.work();
        }
    }

    interface Parent{
        int work();
    }

    class Child1 implements Parent {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }


    class Child2 implements Parent {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

}
