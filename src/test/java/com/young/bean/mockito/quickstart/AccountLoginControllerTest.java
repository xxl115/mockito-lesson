package com.young.bean.mockito.quickstart;

import com.young.bean.mockito.common.Account;
import com.young.bean.mockito.common.AccountDao;
import com.young.bean.mockito.common.AccountLoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountLoginControllerTest {

    private AccountDao accountDao;

    private HttpServletRequest request;

    private AccountLoginController accountLoginController;

    @Before
    public void setup() {
        accountDao = mock(AccountDao.class);
        request = mock(HttpServletRequest.class);
        accountLoginController = new AccountLoginController(accountDao);
    }

    @Test
    public void testLoginSuccess() {
        Account account = new Account();
        when(request.getParameter("username")).thenReturn("young");
        when(request.getParameter("password")).thenReturn("123");
        when(accountDao.findAccount(anyString(), anyString())).thenReturn(account);
        String result = accountLoginController.login(request);
        assertEquals("/index.jsp", result);
    }

    @Test
    public void testLoginFailure() {
        when(request.getParameter("username")).thenReturn("young");
        when(request.getParameter("password")).thenReturn("123");
        when(accountDao.findAccount(anyString(), anyString())).thenReturn(null);
        assertEquals("/login",accountLoginController.login(request));
    }

    @Test
    public void testLogin505() {
        when(request.getParameter("username")).thenReturn("young");
        when(request.getParameter("password")).thenReturn("123");
//        when(accountDao.findAccount(anyString(), anyString())).thenThrow(UnsupportedOperationException.class);
        doThrow(UnsupportedOperationException.class).when(accountDao).findAccount(anyString(), anyString());
        assertEquals("/505",accountLoginController.login(request));
    }
}
