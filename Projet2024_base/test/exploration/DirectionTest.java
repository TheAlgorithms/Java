/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package exploration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jb116317
 */
public class DirectionTest {
    
    public DirectionTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getDirection method, of class Direction.
     */
    @Test
    public void testGetDirection_int() {
        System.out.println("getDirection");
        int rang = 0;
        Direction expResult = null;
        Direction result = Direction.getDirection(rang);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirection method, of class Direction.
     */
    @Test
    public void testGetDirection_int_int() {
        System.out.println("getDirection");
        int dLig = 0;
        int dCol = 0;
        Direction expResult = null;
        Direction result = Direction.getDirection(dLig, dCol);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirection method, of class Direction.
     */
    @Test
    public void testGetDirection_String() {
        System.out.println("getDirection");
        String dirTexte = "";
        Direction expResult = null;
        Direction result = Direction.getDirection(dirTexte);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValide method, of class Direction.
     */
    @Test
    public void testIsValide() {
        System.out.println("isValide");
        Direction instance = new Direction();
        boolean expResult = false;
        boolean result = instance.isValide();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getdLig method, of class Direction.
     */
    @Test
    public void testGetdLig() {
        System.out.println("getdLig");
        Direction instance = new Direction();
        int expResult = 0;
        int result = instance.getdLig();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getdCol method, of class Direction.
     */
    @Test
    public void testGetdCol() {
        System.out.println("getdCol");
        Direction instance = new Direction();
        int expResult = 0;
        int result = instance.getdCol();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRang method, of class Direction.
     */
    @Test
    public void testGetRang() {
        System.out.println("getRang");
        Direction instance = new Direction();
        int expResult = 0;
        int result = instance.getRang();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSucc method, of class Direction.
     */
    @Test
    public void testGetSucc() {
        System.out.println("getSucc");
        Direction instance = new Direction();
        Direction expResult = null;
        Direction result = instance.getSucc();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPred method, of class Direction.
     */
    @Test
    public void testGetPred() {
        System.out.println("getPred");
        Direction instance = new Direction();
        Direction expResult = null;
        Direction result = instance.getPred();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCombinaison method, of class Direction.
     */
    @Test
    public void testGetCombinaison() {
        System.out.println("getCombinaison");
        Direction autre = null;
        Direction instance = new Direction();
        Direction expResult = null;
        Direction result = instance.getCombinaison(autre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInverse method, of class Direction.
     */
    @Test
    public void testGetInverse() {
        System.out.println("getInverse");
        Direction instance = new Direction();
        Direction expResult = null;
        Direction result = instance.getInverse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Direction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Direction instance = new Direction();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Direction.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object autre = null;
        Direction instance = new Direction();
        boolean expResult = false;
        boolean result = instance.equals(autre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
