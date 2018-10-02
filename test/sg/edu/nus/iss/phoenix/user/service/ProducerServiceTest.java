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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sg.edu.nus.iss.phoenix.authenticate.dao.PresentorDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.ProducerDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author sourcepirate
 */
public class ProducerServiceTest {
    
    @Mock
    ProducerDao dao;
    
    public ProducerServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException, NotFoundException {
        
        this.dao = mock(ProducerDao.class);
        
        List<User> users = new ArrayList<>();
        users.add(new User());
        
        when(this.dao.getAll()).thenReturn(users);
        when(this.dao.selectProducer("21231232")).thenReturn(users.get(0));
        when(this.dao.searchMatching("hello")).thenReturn(users);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getProducer method, of class ProducerService.
     */
    @Test
    public void testGetProducer() throws Exception {
        System.out.println("getProducer");
        String id = "21231232";
        ProducerService instance = new ProducerService(this.dao);
        User expResult = null;
        User result = instance.getProducer(id);
        assertNotNull(result);

    }

    /**
     * Test of searchProducer method, of class ProducerService.
     */
    @Test
    public void testSearchProducer() throws Exception {
        System.out.println("searchProducer");
        String prefix = "21231232";
        ProducerService instance = new ProducerService(this.dao);
        List<User> expResult = null;
        List<User> result = instance.searchProducer(prefix);
        assertNotNull(result);
    }

    /**
     * Test of getAll method, of class ProducerService.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        ProducerService instance = new ProducerService(this.dao);
        List<User> expResult = null;
        List<User> result = instance.getAll();
        assertNotNull(result);
    }
    
}
