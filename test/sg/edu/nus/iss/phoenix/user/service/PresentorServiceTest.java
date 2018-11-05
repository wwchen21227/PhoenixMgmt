/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import sg.edu.nus.iss.phoenix.authenticate.dao.PresentorDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author sourcepirate
 */
public class PresentorServiceTest {
    
    
    @Mock
    PresentorDao dao;
   
    
    public PresentorServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException, NotFoundException {
        
        this.dao = mock(PresentorDao.class);
        
        List<User> users = new ArrayList<>();
        users.add(new User());
        
        when(this.dao.getAll()).thenReturn(users);
        when(this.dao.selectPresentor("21231232")).thenReturn(users.get(0));
        when(this.dao.searchMatching("hello")).thenReturn(users);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPresentor method, of class PresentorService.
     */
    @Test
    public void testGetPresentor() throws Exception {
        System.out.println("getPresentor");
        String id = "21231232";
        PresentorService instance = new PresentorService(this.dao);
        User expResult = null;
        User result = instance.getPresentor(id);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of searchPresentor method, of class PresentorService.
     */
    @Test
    public void testSearchPresentor() throws Exception {
        System.out.println("searchPresentor");
        String prefix = "";
        PresentorService instance = new PresentorService(this.dao);
        List<User> expResult = null;
        List<User> result = instance.searchPresentor(prefix);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getAll method, of class PresentorService.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        PresentorService instance = new PresentorService(this.dao);
        List<User> expResult = null;
        List<User> result = instance.getAll();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
