/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.controller;


import java.io.IOException;
import java.util.*;
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
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 *
 * @author sourcepirate
 */
public class ManageRadioProgramCmdTest {
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
    
    @Mock
    ManageRadioProgramCmd cmd;
    
    
    User user = new User();
    
    RadioProgram program = new RadioProgram();
    
    public ManageRadioProgramCmdTest() {
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
        this.rsDel = mock(DeleteRadioProgramCmd.class);
        this.pDel = mock(ProgramDelegate.class);
        this.rDel = mock(ReviewSelectProgramDelegate.class); 
        this.cmd = mock(ManageRadioProgramCmd.class);
        
        
        List<RadioProgram> list = new ArrayList<>();
        
        
        when(this.cmd.getRSPDelegate()).thenReturn(this.rDel);
        when(this.rDel.reviewSelectRadioProgram()).thenReturn(list);
        when(this.cmd.perform("", this.req, null)).thenReturn("Manage");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of perform method, of class ManageRadioProgramCmd.
     */
    @Test
    public void testPerform() throws Exception {
        System.out.println("perform");
        String path = "";
        HttpServletRequest req = this.req;
        HttpServletResponse resp = null;
        ManageRadioProgramCmd instance = this.cmd;
        String expResult = "";
        String result = instance.perform(path, req, resp);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
