/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.controller;

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
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author sourcepirate
 */
public class AddEditRadioProgramCmdTest {
    
     @Mock
    HttpServletRequest req;
     
    
    @Mock
    HttpSession session;
    
    
    User user = new User();
    
    public AddEditRadioProgramCmdTest() {
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

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of perform method, of class AddEditRadioProgramCmd.
     */
    @Test
    public void testPerform() throws Exception {
        System.out.println("perform");
        String path = "";
        HttpServletRequest req = null;
        HttpServletResponse resp = null;
        AddEditRadioProgramCmd instance = new AddEditRadioProgramCmd();
        String expResult = "";
        String result = instance.perform(path, req, resp);
        assertNotNull(result);
    }
    
}
