/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.entity;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sourcepirate
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        User instance = new User();
        String expResult = "";
        instance.setId(expResult);
        String result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setId method, of class User.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String idIn = "";
        User instance = new User();
        instance.setId(idIn);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User();
        String expResult = "";
        instance.setPassword(expResult);
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String passwordIn = "";
        User instance = new User();
        instance.setPassword(passwordIn);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        User instance = new User();
        String expResult = "";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String nameIn = "";
        User instance = new User();
        instance.setName(nameIn);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getRoles method, of class User.
     */
    @Test
    public void testGetRoles() {
        System.out.println("getRoles");
        User instance = new User();
        ArrayList<Role> expResult = null;
        instance.setRoles(expResult);
        ArrayList<Role> result = instance.getRoles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setRoles method, of class User.
     */
    @Test
    public void testSetRoles() {
        System.out.println("setRoles");
        ArrayList<Role> roles = null;
        User instance = new User();
        instance.setRoles(roles);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setAll method, of class User.
     */
    @Test
    public void testSetAll() {
        System.out.println("setAll");
        String idIn = "";
        String passwordIn = "";
        String nameIn = "";
        String roleIn = "";
        User instance = new User();
        instance.setAll(idIn, passwordIn, nameIn, roleIn);
        // TODO review the generated test code and remove the default call to fail.
    }

    
}
