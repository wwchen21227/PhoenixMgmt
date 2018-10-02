/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import java.io.IOException;
import javax.servlet.ServletException;
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
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;

/**
 *
 * @author sourcepirate
 */
public class DeleteAnnualScheduleCmdTest {
    
    @Mock
    ScheduleDelegate delegate;
    
     @Mock
    HttpServletRequest req;
    
    @Mock
    HttpSession session;
    
    
    @Mock
    DeleteAnnualScheduleCmd cmd;
    
    
    public DeleteAnnualScheduleCmdTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException, ServletException {
        this.req = mock(HttpServletRequest.class);
        this.session = mock(HttpSession.class);
        this.delegate = mock(ScheduleDelegate.class);
        this.cmd = mock(DeleteAnnualScheduleCmd.class);
        when(this.req.getParameter("year")).thenReturn("2018");
        when(this.cmd.getDelegate()).thenReturn(this.delegate);
        when(this.cmd.perform("", this.req, null)).thenReturn("pageroute");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of perform method, of class DeleteAnnualScheduleCmd.
     */
    @Test
    public void testPerform() throws Exception {
        System.out.println("perform");
        String path = "";
        HttpServletRequest req = this.req;
        HttpServletResponse resp = null;
        DeleteAnnualScheduleCmd instance = this.cmd;
        String expResult = "";
        String result = instance.perform(path, req, resp);
        assertNotNull(result);
    }
    
}
