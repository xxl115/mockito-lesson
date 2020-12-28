package com.young.bean.mockito.lesson03;

import com.young.bean.mockito.common.Account;
import com.young.bean.mockito.common.AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;

public class MockByAnnotationTest {

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private AccountDao accountDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void testMock() {
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}
