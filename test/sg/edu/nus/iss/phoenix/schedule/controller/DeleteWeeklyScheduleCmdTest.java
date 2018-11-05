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
public class DeleteWeeklyScheduleCmdTest {
    
    @Mock
    ScheduleDelegate delegate;
    
    @Mock
    HttpServletRequest req;
    
    @Mock
    HttpSession session;
    
    @Mock
    DeleteWeeklyScheduleCmd cmd;
    
    public DeleteWeeklyScheduleCmdTest() {
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
        this.cmd = mock(DeleteWeeklyScheduleCmd.class);
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse("28-08-2018");   
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
        
         when(this.req.getParameter("dateOfProgram")).thenReturn("28-08-2018");
        when(this.cmd.getDelegate()).thenReturn(this.delegate);
        Mockito.doNothing().when(this.delegate).processDeleteWS(sqlStartDate);
        when(this.cmd.perform("", this.req, null)).thenReturn("Somepage");

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of perform method, of class DeleteWeeklyScheduleCmd.
     */
    @Test
    public void testPerform() throws Exception {
        System.out.println("perform");
        String path = "";
        HttpServletRequest req = this.req;
        HttpServletResponse resp = null;
        DeleteWeeklyScheduleCmd instance = this.cmd;
        String expResult = "";
        String result = instance.perform(path, req, resp);
        assertNotNull(result);
    }

    
}
