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
public class CursorLinkedListTest {
    
    public CursorLinkedListTest() {
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
     * Test of printList method, of class CursorLinkedList.
     */
    @Test
    public void testPrintList() {
        System.out.println("printList");
        CursorLinkedList instance = new CursorLinkedList();
        instance.printList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexOf method, of class CursorLinkedList.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        Object element = null;
        CursorLinkedList instance = new CursorLinkedList();
        int expResult = 0;
        int result = instance.indexOf(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class CursorLinkedList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int position = 0;
        CursorLinkedList instance = new CursorLinkedList();
        Object expResult = null;
        Object result = instance.get(position);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeByIndex method, of class CursorLinkedList.
     */
    @Test
    public void testRemoveByIndex() {
        System.out.println("removeByIndex");
        int index = 0;
        CursorLinkedList instance = new CursorLinkedList();
        instance.removeByIndex(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class CursorLinkedList.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Object element = null;
        CursorLinkedList instance = new CursorLinkedList();
        instance.remove(element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of append method, of class CursorLinkedList.
     */
    @Test
    public void testAppend() {
        System.out.println("append");
        Object element = null;
        CursorLinkedList instance = new CursorLinkedList();
        instance.append(element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
