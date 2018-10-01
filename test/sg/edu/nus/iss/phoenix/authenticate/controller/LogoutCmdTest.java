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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author sourcepirate
 */
public class LogoutCmdTest {
   
    
    @Mock
    HttpServletRequest req;
    
    @Mock
    HttpSession session;
    
    
    User user = new User();
    
    public LogoutCmdTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.req = mock(HttpServletRequest.class);
        this.session = mock(HttpSession.class);
        when(this.req.getSession()).thenReturn(this.session);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of perform method, of class LogoutCmd.
     */
    @Test
    public void testPerform() throws Exception {
        System.out.println("perform");
        String path = "";
        HttpServletResponse resp = null;
        LogoutCmd instance = new LogoutCmd();
        String expResult = "";
        String result = instance.perform(path, this.req, resp);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
