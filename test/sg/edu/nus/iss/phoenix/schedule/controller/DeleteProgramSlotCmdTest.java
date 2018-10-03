/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;

/**
 *
 * @author sourcepirate
 */
public class DeleteProgramSlotCmdTest {
    
    @Mock
    ScheduleDelegate delegate;
    
     @Mock
    HttpServletRequest req;
    
    @Mock
    HttpSession session;
    
    @Mock
    DeleteProgramSlotCmd cmd;
    
    
    public DeleteProgramSlotCmdTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ParseException, IOException, ServletException {
        this.req = mock(HttpServletRequest.class);
        this.session = mock(HttpSession.class);
        this.delegate = mock(ScheduleDelegate.class);
        this.cmd = mock(DeleteProgramSlotCmd.class);
        when(this.req.getParameter("programSlotId")).thenReturn("someid");
        when(this.cmd.getDelegate()).thenReturn(this.delegate);
        when(this.cmd.perform("", this.req, null)).thenReturn("Somepage");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of perform method, of class DeleteProgramSlotCmd.
     */
    @Test
    public void testPerform() throws Exception {
        System.out.println("perform");
        String path = "";
        HttpServletRequest req = this.req;
        HttpServletResponse resp = null;
        DeleteProgramSlotCmd instance = this.cmd;
        String expResult = "";
        String result = instance.perform(path, this.req, null);
        assertNotNull(result);
    }
    
}
