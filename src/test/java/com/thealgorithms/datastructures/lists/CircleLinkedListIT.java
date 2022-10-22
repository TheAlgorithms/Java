/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.thealgorithms.datastructures.lists;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ilya
 */
public class CircleLinkedListIT {
    
    public CircleLinkedListIT() {
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
     * Test of getSize method, of class CircleLinkedList.
     */
    @Test
    public void fuflo() {
        
        CircleLinkedList<Integer> cll = new CircleLinkedList<Integer>();
        cll.append(1);
        cll.append(2);
        cll.append(3);
        cll.append(4);
        cll.append(5);
        String h = cll.toString();
        int a = cll.getSize();
        int b = 0;
        
    }

    /**
     * Test of getSize method, of class CircleLinkedList.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        CircleLinkedList instance = new CircleLinkedList();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of append method, of class CircleLinkedList.
     */
    @Test
    public void testAppend() {
        System.out.println("append");
        Object value = null;
        CircleLinkedList instance = new CircleLinkedList();
        instance.append(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class CircleLinkedList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CircleLinkedList instance = new CircleLinkedList();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class CircleLinkedList.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        int pos = 0;
        CircleLinkedList instance = new CircleLinkedList();
        Object expResult = null;
        Object result = instance.remove(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class CircleLinkedList.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        CircleLinkedList.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
