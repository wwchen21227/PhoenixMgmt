/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.entity;

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
public class RoleTest {
    
    public RoleTest() {
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
     * Test of getRole method, of class Role.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        Role instance = new Role();
        String expResult = "";
        instance.setRole(expResult);
        String result = instance.getRole();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setRole method, of class Role.
     */
    @Test
    public void testSetRole() {
        System.out.println("setRole");
        String roleIn = "";
        Role instance = new Role();
        instance.setRole(roleIn);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getAccessPrivilege method, of class Role.
     */
    @Test
    public void testGetAccessPrivilege() {
        System.out.println("getAccessPrivilege");
        Role instance = new Role();
        String expResult = "";
        instance.setAccessPrivilege(expResult);
        String result = instance.getAccessPrivilege();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setAccessPrivilege method, of class Role.
     */
    @Test
    public void testSetAccessPrivilege() {
        System.out.println("setAccessPrivilege");
        String accessPrivilegeIn = "";
        Role instance = new Role();
        instance.setAccessPrivilege(accessPrivilegeIn);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setAll method, of class Role.
     */
    @Test
    public void testSetAll() {
        System.out.println("setAll");
        String roleIn = "";
        String accessPrivilegeIn = "";
        Role instance = new Role();
        instance.setAll(roleIn, accessPrivilegeIn);
        // TODO review the generated test code and remove the default call to fail.
        
    }
}
