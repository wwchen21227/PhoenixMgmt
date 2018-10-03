/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author sourcepirate
 */
public class LoginCmdTest {
    
    @Mock
    AuthenticateDelegate delegate;
    
    @Mock
    HttpServletRequest req;
    
    @Mock
    HttpSession session;
    
    
    User user = new User();
    
    public LoginCmdTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.delegate = mock(AuthenticateDelegate.class);
        this.req = mock(HttpServletRequest.class);
        this.session = mock(HttpSession.class);
        
        when(this.req.getParameter("id")).thenReturn("id");
        when(this.req.getParameter("password")).thenReturn("password");
        when(this.delegate.validateUserIdPassword(this.user)).thenReturn(this.user);
        when(this.req.getSession()).thenReturn(this.session);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of perform method, of class LoginCmd.
     */
    @Test
    public void testPerform() throws Exception {
        System.out.println("perform");
        String path = "";
        HttpServletResponse resp = null;
        LoginCmd instance = new LoginCmd(this.delegate);
        String expResult = "";
        String result = instance.perform(path, this.req, resp);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
