package com.young.bean.mockito.lesson03;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeepMockTest {

    @Mock
    private Lesson03Service lesson03Service;


    @Mock
    private Lesson03 lesson03;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock() {
        when(lesson03Service.get()).thenReturn(lesson03);
        Lesson03 l03 = lesson03Service.get();
        l03.foo();
    }


    @Test
    public void testDeepMock() {
        Lesson03Service mock = mock(Lesson03Service.class, Answers.RETURNS_DEEP_STUBS);
        mock.get().foo();
    }
}
