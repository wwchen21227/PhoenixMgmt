/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.delegate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.authenticate.service.AuthenticateService;

/**
 *
 * @author sourcepirate
 */
public class AuthenticateDelegateTest {
    
    
    @Mock
    AuthenticateService service;
    
    
    User user = new User();
    
    
    public AuthenticateDelegateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
     this.service = mock(AuthenticateService.class);
     when(this.service.validateUserIdPassword(null)).thenReturn(null);
     when(this.service.validateUserIdPassword(this.user)).thenReturn(this.user);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateUserIdPassword method, of class AuthenticateDelegate.
     */
    @Test
    public void testValidateUserIdPassword() {
        System.out.println("validateUserIdPassword");
        User user = null;
        AuthenticateDelegate instance = new AuthenticateDelegate(this.service);
        User expResult = null;
        User result = instance.validateUserIdPassword(null);
        assertEquals(expResult, result);
        User result1 = instance.validateUserIdPassword(this.user);
        assertNotNull(result1);

    }

    /**
     * Test of evaluateAccessPreviledge method, of class AuthenticateDelegate.
     */
    @Test
    public void testEvaluateAccessPreviledge() {
        System.out.println("evaluateAccessPreviledge");
        User user = null;
        AuthenticateDelegate instance = new AuthenticateDelegate(this.service);
        User expResult = null;
        User result = instance.evaluateAccessPreviledge(user);
        assertEquals(expResult, result);
    }
    
}
