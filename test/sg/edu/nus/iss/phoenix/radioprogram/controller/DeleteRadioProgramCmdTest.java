/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.controller;

import java.util.List;
import java.util.ArrayList;
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
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 *
 * @author sourcepirate
 */
public class DeleteRadioProgramCmdTest {
    
    @Mock
    HttpServletRequest req;
    
    @Mock
    HttpSession session;
    
    @Mock
    DeleteRadioProgramCmd rsDel;
    
    @Mock
    ProgramDelegate pDel;
    
    @Mock
    ReviewSelectProgramDelegate rDel;
    
    User user = new User();
    
    public DeleteRadioProgramCmdTest() {
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
        this.rsDel = mock(DeleteRadioProgramCmd.class);
        this.pDel = mock(ProgramDelegate.class);
        this.rDel = mock(ReviewSelectProgramDelegate.class);
        
        List<RadioProgram> list = new ArrayList<RadioProgram>();
       
        when(this.rsDel.getProgramDelegate()).thenReturn(this.pDel);
        when(this.rsDel.getReviewSelectDelegate()).thenReturn(this.rDel);
        when(this.rDel.reviewSelectRadioProgram()).thenReturn(list);
        when(this.req.getParameter("name")).thenReturn("hello");
       
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of perform method, of class DeleteRadioProgramCmd.
     */
    @Test
    public void testPerform() throws Exception {
        System.out.println("perform");
        String path = "";
        HttpServletResponse resp = null;
        DeleteRadioProgramCmd instance = this.rsDel;
        String expResult = "";
        String result = instance.perform(path, this.req, resp);
        assertNull(result);
    }

}
