package com.young.bean.mockito.lesson03;

import com.young.bean.mockito.common.Account;
import com.young.bean.mockito.common.AccountDao;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class MockByRunnerTest {

    @Test
    public void testMock() {
        AccountDao accountDao = mock(AccountDao.class);
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}
