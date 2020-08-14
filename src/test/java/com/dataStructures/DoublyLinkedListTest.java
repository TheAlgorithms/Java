package com.dataStructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> dList = new DoublyLinkedList<>();

    @Test
    public void testEmptyList(){
        assertTrue(dList.isEmpty());
        dList.showList();
    }

    @Test
    public void headInsertionTest(){
        dList.insertHead(10);
        dList.insertHead(20);
        assertEquals(2, dList.getSize());
        dList.showList();

        dList.insertHead(30);
        dList.insertHead(40);
        assertEquals(4, dList.getSize());
        dList.showList();
    }

    @Test
    public void deleteTest(){
        dList.insertHead(1);
        dList.insertHead(15);
        dList.insertHead(6);
        dList.insertHead(1);
        dList.insertHead(2);
        dList.showList();

        dList.delete(1);
        assertEquals(4, dList.getSize());
        dList.showList();

        dList.deleteHead();
        dList.deleteHead();
        assertEquals(2, dList.getSize());
        dList.showList();

        dList.deleteHead();
        dList.deleteHead();
        assertTrue(dList.isEmpty());
        dList.showList();
    }
    
}